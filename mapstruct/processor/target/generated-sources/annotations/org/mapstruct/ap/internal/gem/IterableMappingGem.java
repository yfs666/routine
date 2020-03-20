package org.mapstruct.ap.internal.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.AbstractAnnotationValueVisitor8;
import javax.lang.model.util.ElementFilter;
import org.mapstruct.tools.gem.Gem;
import org.mapstruct.tools.gem.GemValue;

import javax.lang.model.type.TypeMirror;

public class IterableMappingGem implements Gem {

    private final GemValue<String> dateFormat;
    private final GemValue<String> numberFormat;
    private final GemValue<List<TypeMirror>> qualifiedBy;
    private final GemValue<List<String>> qualifiedByName;
    private final GemValue<TypeMirror> elementTargetType;
    private final GemValue<String> nullValueMappingStrategy;
    private final GemValue<TypeMirror> elementMappingControl;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private IterableMappingGem( BuilderImpl builder ) {
        this.dateFormat = builder.dateFormat;
        this.numberFormat = builder.numberFormat;
        this.qualifiedBy = builder.qualifiedBy;
        this.qualifiedByName = builder.qualifiedByName;
        this.elementTargetType = builder.elementTargetType;
        this.nullValueMappingStrategy = builder.nullValueMappingStrategy;
        this.elementMappingControl = builder.elementMappingControl;
        isValid = ( this.dateFormat != null ? this.dateFormat.isValid() : false )
               && ( this.numberFormat != null ? this.numberFormat.isValid() : false )
               && ( this.qualifiedBy != null ? this.qualifiedBy.isValid() : false )
               && ( this.qualifiedByName != null ? this.qualifiedByName.isValid() : false )
               && ( this.elementTargetType != null ? this.elementTargetType.isValid() : false )
               && ( this.nullValueMappingStrategy != null ? this.nullValueMappingStrategy.isValid() : false )
               && ( this.elementMappingControl != null ? this.elementMappingControl.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#dateFormat}
    */
    public GemValue<String> dateFormat( ) {
        return dateFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#numberFormat}
    */
    public GemValue<String> numberFormat( ) {
        return numberFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#qualifiedBy}
    */
    public GemValue<List<TypeMirror>> qualifiedBy( ) {
        return qualifiedBy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#qualifiedByName}
    */
    public GemValue<List<String>> qualifiedByName( ) {
        return qualifiedByName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#elementTargetType}
    */
    public GemValue<TypeMirror> elementTargetType( ) {
        return elementTargetType;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#nullValueMappingStrategy}
    */
    public GemValue<String> nullValueMappingStrategy( ) {
        return nullValueMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link IterableMappingGem#elementMappingControl}
    */
    public GemValue<TypeMirror> elementMappingControl( ) {
        return elementMappingControl;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static IterableMappingGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static IterableMappingGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.IterableMapping".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
            .findAny()
            .orElse( null );
        return build( mirror, builder );
    }

    public static <T> T build(AnnotationMirror mirror, Builder<T> builder ) {

        // return fast
        if ( mirror == null || builder == null ) {
            return null;
        }

        // fetch defaults from all defined values in the annotation type
        List<ExecutableElement> enclosed = ElementFilter.methodsIn( mirror.getAnnotationType().asElement().getEnclosedElements() );
        Map<String, AnnotationValue> defaultValues = new HashMap<>( enclosed.size() );
        enclosed.forEach( e -> defaultValues.put( e.getSimpleName().toString(), e.getDefaultValue() ) );

        // fetch all explicitely set annotation values in the annotation instance
        Map<String, AnnotationValue> values = new HashMap<>( enclosed.size() );
        mirror.getElementValues().entrySet().forEach( e -> values.put( e.getKey().getSimpleName().toString(), e.getValue() ) );

        // iterate and populate builder
        for ( String methodName : defaultValues.keySet() ) {

            if ( "dateFormat".equals( methodName ) ) {
                builder.setDateformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "numberFormat".equals( methodName ) ) {
                builder.setNumberformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "qualifiedBy".equals( methodName ) ) {
                builder.setQualifiedby( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "qualifiedByName".equals( methodName ) ) {
                builder.setQualifiedbyname( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "elementTargetType".equals( methodName ) ) {
                builder.setElementtargettype( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "nullValueMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluemappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "elementMappingControl".equals( methodName ) ) {
                builder.setElementmappingcontrol( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
        }
        builder.setMirror( mirror );
        return builder.build();
    }

    /**
     * A builder that can be implemented by the user to define custom logic e.g. in the
     * build method, prior to creating the annotation gem.
     */
    public interface Builder<T> {

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#dateFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setDateformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#numberFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setNumberformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#qualifiedBy}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setQualifiedby(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#qualifiedByName}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setQualifiedbyname(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#elementTargetType}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setElementtargettype(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#nullValueMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setNullvaluemappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link IterableMappingGem#elementMappingControl}
        *
        * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
        */
        Builder setElementmappingcontrol(GemValue<TypeMirror> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link IterableMappingGem}
         */
          Builder setMirror( AnnotationMirror mirror );

        /**
         * The build method can be overriden in a custom custom implementation, which allows
         * the user to define his own custom validation on the annotation.
         *
         * @return the representation of the annotation
         */
        T build();
    }

    private static class BuilderImpl implements Builder<IterableMappingGem> {

        private GemValue<String> dateFormat;
        private GemValue<String> numberFormat;
        private GemValue<List<TypeMirror>> qualifiedBy;
        private GemValue<List<String>> qualifiedByName;
        private GemValue<TypeMirror> elementTargetType;
        private GemValue<String> nullValueMappingStrategy;
        private GemValue<TypeMirror> elementMappingControl;
        private AnnotationMirror mirror;

        public Builder setDateformat(GemValue<String> dateFormat ) {
            this.dateFormat = dateFormat;
            return this;
        }

        public Builder setNumberformat(GemValue<String> numberFormat ) {
            this.numberFormat = numberFormat;
            return this;
        }

        public Builder setQualifiedby(GemValue<List<TypeMirror>> qualifiedBy ) {
            this.qualifiedBy = qualifiedBy;
            return this;
        }

        public Builder setQualifiedbyname(GemValue<List<String>> qualifiedByName ) {
            this.qualifiedByName = qualifiedByName;
            return this;
        }

        public Builder setElementtargettype(GemValue<TypeMirror> elementTargetType ) {
            this.elementTargetType = elementTargetType;
            return this;
        }

        public Builder setNullvaluemappingstrategy(GemValue<String> nullValueMappingStrategy ) {
            this.nullValueMappingStrategy = nullValueMappingStrategy;
            return this;
        }

        public Builder setElementmappingcontrol(GemValue<TypeMirror> elementMappingControl ) {
            this.elementMappingControl = elementMappingControl;
            return this;
        }

        public Builder  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public IterableMappingGem build() {
            return new IterableMappingGem( this );
        }
    }

}
