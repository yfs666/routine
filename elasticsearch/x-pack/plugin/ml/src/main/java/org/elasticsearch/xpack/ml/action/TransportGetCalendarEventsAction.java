/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.ml.action;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.HandledTransportAction;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.tasks.Task;
import org.elasticsearch.transport.TransportService;
import org.elasticsearch.xpack.core.action.util.QueryPage;
import org.elasticsearch.xpack.core.ml.action.GetCalendarEventsAction;
import org.elasticsearch.xpack.core.ml.calendars.ScheduledEvent;
import org.elasticsearch.xpack.core.ml.job.config.Job;
import org.elasticsearch.xpack.core.ml.utils.ExceptionsHelper;
import org.elasticsearch.xpack.ml.job.persistence.CalendarQueryBuilder;
import org.elasticsearch.xpack.ml.job.persistence.JobConfigProvider;
import org.elasticsearch.xpack.ml.job.persistence.JobResultsProvider;
import org.elasticsearch.xpack.ml.job.persistence.ScheduledEventsQueryBuilder;

import java.util.Collections;

public class TransportGetCalendarEventsAction extends HandledTransportAction<GetCalendarEventsAction.Request,
        GetCalendarEventsAction.Response> {

    private final JobResultsProvider jobResultsProvider;
    private final JobConfigProvider jobConfigProvider;

    @Inject
    public TransportGetCalendarEventsAction(TransportService transportService,
                                            ActionFilters actionFilters, JobResultsProvider jobResultsProvider,
                                            JobConfigProvider jobConfigProvider) {
        super(GetCalendarEventsAction.NAME, transportService, actionFilters, GetCalendarEventsAction.Request::new);
        this.jobResultsProvider = jobResultsProvider;
        this.jobConfigProvider = jobConfigProvider;
    }

    @Override
    protected void doExecute(Task task, GetCalendarEventsAction.Request request,
                             ActionListener<GetCalendarEventsAction.Response> listener) {
        final String[] calendarId = Strings.splitStringByCommaToArray(request.getCalendarId());
        ActionListener<Boolean> calendarExistsListener = ActionListener.wrap(
                r -> {
                    ScheduledEventsQueryBuilder query = new ScheduledEventsQueryBuilder()
                        .start(request.getStart())
                        .end(request.getEnd())
                        .from(request.getPageParams().getFrom())
                        .size(request.getPageParams().getSize())
                        .calendarIds(calendarId);

                    ActionListener<QueryPage<ScheduledEvent>> eventsListener = ActionListener.wrap(
                            events -> {
                                listener.onResponse(new GetCalendarEventsAction.Response(events));
                            },
                            listener::onFailure
                    );

                    if (request.getJobId() != null) {

                        jobConfigProvider.getJob(request.getJobId(), ActionListener.wrap(
                                jobBuilder -> {
                                    Job job = jobBuilder.build();
                                    jobResultsProvider.scheduledEventsForJob(request.getJobId(), job.getGroups(), query, eventsListener);

                                },
                                jobNotFound -> {
                                    // is the request Id a group?
                                    jobConfigProvider.groupExists(request.getJobId(), ActionListener.wrap(
                                            groupExists -> {
                                                if (groupExists) {
                                                    jobResultsProvider.scheduledEventsForJob(
                                                        null,
                                                        Collections.singletonList(request.getJobId()),
                                                        query,
                                                        eventsListener);
                                                } else {
                                                    listener.onFailure(ExceptionsHelper.missingJobException(request.getJobId()));
                                                }
                                            },
                                            listener::onFailure
                                    ));
                                }
                        ));
                    } else {
                        jobResultsProvider.scheduledEvents(query, eventsListener);
                    }
                },
                listener::onFailure);

        checkCalendarExists(calendarId, calendarExistsListener);
    }

    private void checkCalendarExists(String[] calendarId, ActionListener<Boolean> listener) {
        if (Strings.isAllOrWildcard(calendarId)) {
            listener.onResponse(true);
            return;
        }

        jobResultsProvider.calendars(CalendarQueryBuilder.builder().calendarIdTokens(calendarId), ActionListener.wrap(
                c -> listener.onResponse(true),
                listener::onFailure
        ));
    }
}
