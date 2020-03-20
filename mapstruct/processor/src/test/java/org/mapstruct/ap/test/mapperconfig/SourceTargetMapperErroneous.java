/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.mapperconfig;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Sjaak Derksen
 */
@Mapper(uses = { CustomMapperViaMapper.class }, config = CentralConfig.class )
public interface SourceTargetMapperErroneous {

    SourceTargetMapperErroneous INSTANCE = Mappers.getMapper( SourceTargetMapperErroneous.class );

    TargetNoFoo toTarget( Source source );
}
