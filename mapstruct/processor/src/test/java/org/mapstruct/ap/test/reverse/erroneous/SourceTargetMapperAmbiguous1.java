/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.reverse.erroneous;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ap.test.reverse.Source;
import org.mapstruct.ap.test.reverse.Target;
import org.mapstruct.factory.Mappers;

/**
 * @author Sjaak Derksen
 */
@Mapper
public interface SourceTargetMapperAmbiguous1 {

    SourceTargetMapperAmbiguous1 INSTANCE = Mappers.getMapper( SourceTargetMapperAmbiguous1.class );

    @Mappings({
        @Mapping(source = "stringPropX", target = "stringPropY"),
        @Mapping(source = "integerPropX", target = "integerPropY"),
        @Mapping(source = "propertyToIgnoreDownstream", target = "propertyNotToIgnoreUpstream")
    })
    Target forward(Source source);

    @Mappings({
        @Mapping(source = "stringPropX", target = "stringPropY"),
        @Mapping(source = "integerPropX", target = "integerPropY"),
        @Mapping(source = "propertyToIgnoreDownstream", target = "propertyNotToIgnoreUpstream")
    })
    Target forwardNotToReverse(Source source);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "someConstantDownstream", constant = "test"),
        @Mapping(target = "propertyToIgnoreDownstream", ignore = true)
    })
    Source reverse(Target target);
}
