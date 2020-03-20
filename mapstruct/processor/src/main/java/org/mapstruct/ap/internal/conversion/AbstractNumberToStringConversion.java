/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.internal.conversion;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.mapstruct.ap.internal.model.common.ConversionContext;
import org.mapstruct.ap.internal.model.common.Type;

/**
 * Abstract base class for {@link PrimitiveToStringConversion}, {@link WrapperToStringConversion},
 * {@link BigDecimalToStringConversion} and {@link BigIntegerToStringConversion}
 *
 * Contains shared utility methods.
 *
 * @author Ciaran Liedeman
 */
public abstract class AbstractNumberToStringConversion extends SimpleConversion {

    private final boolean sourceTypeNumberSubclass;

    public AbstractNumberToStringConversion(boolean sourceTypeNumberSubclass) {
        this.sourceTypeNumberSubclass = sourceTypeNumberSubclass;
    }

    @Override
    public Set<Type> getToConversionImportTypes(ConversionContext conversionContext) {
        if ( requiresDecimalFormat( conversionContext ) ) {
            return Collections.singleton( conversionContext.getTypeFactory().getType( DecimalFormat.class ) );
        }
        else {
            return super.getToConversionImportTypes( conversionContext );
        }
    }

    protected boolean requiresDecimalFormat(ConversionContext conversionContext) {
        return sourceTypeNumberSubclass && conversionContext.getNumberFormat() != null;
    }

    @Override
    protected Set<Type> getFromConversionImportTypes(ConversionContext conversionContext) {
        if ( requiresDecimalFormat( conversionContext ) ) {
            return Collections.singleton( conversionContext.getTypeFactory().getType( DecimalFormat.class ) );
        }
        else {
            return super.getFromConversionImportTypes( conversionContext );
        }
    }

    @Override
    protected List<Type> getFromConversionExceptionTypes(ConversionContext conversionContext) {
        if ( requiresDecimalFormat( conversionContext ) ) {
            return Collections.singletonList( conversionContext.getTypeFactory().getType( ParseException.class ) );
        }
        else {
            return super.getFromConversionExceptionTypes( conversionContext );
        }
    }
}
