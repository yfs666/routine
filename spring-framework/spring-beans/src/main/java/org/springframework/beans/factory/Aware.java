/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory;

/**
 * A marker superinterface indicating that a bean is eligible to be notified by the
 * Spring container of a particular framework object through a callback-style method.
 * The actual method signature is determined by individual subinterfaces but should
 * typically consist of just one void-returning method that accepts a single argument.
 *
 * <p>Note that merely implementing {@link Aware} provides no default functionality.
 * Rather, processing must be done explicitly, for example in a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor}.
 * Refer to {@link org.springframework.context.support.ApplicationContextAwareProcessor}
 * for an example of processing specific {@code *Aware} interface callbacks.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 *
 *
		LoadTimeWeaverAware：加载Spring Bean时织入第三方模块，如AspectJ
		BeanClassLoaderAware：加载Spring Bean的类加载器
		BootstrapContextAware：资源适配器BootstrapContext，如JCA,CCI
		ResourceLoaderAware：底层访问资源的加载器
		BeanFactoryAware：声明BeanFactory
		PortletConfigAware：PortletConfig
		PortletContextAware：PortletContext
		ServletConfigAware：ServletConfig
		ServletContextAware：ServletContext
		MessageSourceAware：国际化
		ApplicationEventPublisherAware：应用事件
		NotificationPublisherAware：JMX通知
		BeanNameAware：声明Spring Bean的名字

 *
 */
public interface Aware {

}
