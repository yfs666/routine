/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.bugs._1130;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.TargetType;
import org.mapstruct.ap.test.bugs._1130.Issue1130Mapper.ADto;
import org.mapstruct.ap.test.bugs._1130.Issue1130Mapper.AEntity;
import org.mapstruct.ap.test.bugs._1130.Issue1130Mapper.BEntity;
import org.mapstruct.ap.testutil.IssueKey;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;
import org.mapstruct.factory.Mappers;

/**
 * Tests that when calling an update method for a previously null property, the factory method is called even if that
 * factory method has a {@link TargetType} annotation.
 *
 * @author Andreas Gudian
 */
@IssueKey("1130")
@RunWith(AnnotationProcessorTestRunner.class)
@WithClasses(Issue1130Mapper.class)
public class Issue1130Test {
    @Test
    public void factoryMethodWithTargetTypeInUpdateMethods() {
        AEntity aEntity = new AEntity();
        aEntity.setB( new BEntity() );

        ADto aDto = new ADto();
        Mappers.getMapper( Issue1130Mapper.class ).mergeA( aEntity, aDto );

        assertThat( aDto.getB() ).isNotNull();
        assertThat( aDto.getB().getPassedViaConstructor() ).isEqualTo( "created by factory" );
    }
}
