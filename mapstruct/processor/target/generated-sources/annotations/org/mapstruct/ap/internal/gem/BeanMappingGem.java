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

public class BeanMappingGem implements Gem {

    private final GemValue<TypeMirror> resultType;
    private final GemValue<List<TypeMirror>> qualifiedBy;
    private final GemValue<List<String>> qualifiedByName;
    private final GemValue<String> nullValueMappingStrategy;
    private final GemValue<String> nullValuePropertyMappingStrategy;
    private final GemValue<String> nullValueCheckStrategy;
    private final GemValue<Boolean> ignoreByDefault;
    private final GemValue<List<String>> ignoreUnmappedSourceProperties;
    private final GemValue<BuilderGem> builder;
    private final GemValue<TypeMirror> mappingControl;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private BeanMappingGem( BuilderImpl builder ) {
        this.resultType = builder.resultType;
        this.qualifiedBy = builder.qualifiedBy;
        this.qualifiedByName = builder.qualifiedByName;
        this.nullValueMappingStrategy = builder.nullValueMappingStrategy;
        this.nullValuePropertyMappingStrategy = builder.nullValuePropertyMappingStrategy;
        this.nullValueCheckStrategy = builder.nullValueCheckStrategy;
        this.ignoreByDefault = builder.ignoreByDefault;
        this.ignoreUnmappedSourceProperties = builder.ignoreUnmappedSourceProperties;
        this.builder = builder.builder;
        this.mappingControl = builder.mappingControl;
        isValid = ( this.resultType != null ? this.resultType.isValid() : false )
               && ( this.qualifiedBy != null ? this.qualifiedBy.isValid() : false )
               && ( this.qualifiedByName != null ? this.qualifiedByName.isValid() : false )
               && ( this.nullValueMappingStrategy != null ? this.nullValueMappingStrategy.isValid() : false )
               && ( this.nullValuePropertyMappingStrategy != null ? this.nullValuePropertyMappingStrategy.isValid() : false )
               && ( this.nullValueCheckStrategy != null ? this.nullValueCheckStrategy.isValid() : false )
               && ( this.ignoreByDefault != null ? this.ignoreByDefault.isValid() : false )
               && ( this.ignoreUnmappedSourceProperties != null ? this.ignoreUnmappedSourceProperties.isValid() : false )
               && ( this.builder != null ? this.builder.isValid() : false )
               && ( this.mappingControl != null ? this.mappingControl.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#resultType}
    */
    public GemValue<TypeMirror> resultType( ) {
        return resultType;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#qualifiedBy}
    */
    public GemValue<List<TypeMirror>> qualifiedBy( ) {
        return qualifiedBy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#qualifiedByName}
    */
    public GemValue<List<String>> qualifiedByName( ) {
        return qualifiedByName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#nullValueMappingStrategy}
    */
    public GemValue<String> nullValueMappingStrategy( ) {
        return nullValueMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#nullValuePropertyMappingStrategy}
    */
    public GemValue<String> nullValuePropertyMappingStrategy( ) {
        return nullValuePropertyMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#nullValueCheckStrategy}
    */
    public GemValue<String> nullValueCheckStrategy( ) {
        return nullValueCheckStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#ignoreByDefault}
    */
    public GemValue<Boolean> ignoreByDefault( ) {
        return ignoreByDefault;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#ignoreUnmappedSourceProperties}
    */
    public GemValue<List<String>> ignoreUnmappedSourceProperties( ) {
        return ignoreUnmappedSourceProperties;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#builder}
    */
    public GemValue<BuilderGem> builder( ) {
        return builder;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BeanMappingGem#mappingControl}
    */
    public GemValue<TypeMirror> mappingControl( ) {
        return mappingControl;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static BeanMappingGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static BeanMappingGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.BeanMapping".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "resultType".equals( methodName ) ) {
                builder.setResulttype( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "qualifiedBy".equals( methodName ) ) {
                builder.setQualifiedby( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "qualifiedByName".equals( methodName ) ) {
                builder.setQualifiedbyname( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "nullValueMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluemappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "nullValuePropertyMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluepropertymappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "nullValueCheckStrategy".equals( methodName ) ) {
                builder.setNullvaluecheckstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "ignoreByDefault".equals( methodName ) ) {
                builder.setIgnorebydefault( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), Boolean.class ) );
            }
            else if ( "ignoreUnmappedSourceProperties".equals( methodName ) ) {
                builder.setIgnoreunmappedsourceproperties( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "builder".equals( methodName ) ) {
                builder.setBuilder( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), BuilderGem::instanceOn ) );
            }
            else if ( "mappingControl".equals( methodName ) ) {
                builder.setMappingcontrol( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
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
        * Sets the {@link GemValue} for {@link BeanMappingGem#resultType}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setResulttype(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#qualifiedBy}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setQualifiedby(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#qualifiedByName}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setQualifiedbyname(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#nullValueMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setNullvaluemappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#nullValuePropertyMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setNullvaluepropertymappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#nullValueCheckStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setNullvaluecheckstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#ignoreByDefault}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setIgnorebydefault(GemValue<Boolean> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#ignoreUnmappedSourceProperties}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setIgnoreunmappedsourceproperties(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#builder}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setBuilder(GemValue<BuilderGem> methodName );

       /**
        * Sets the {@link GemValue} for {@link BeanMappingGem#mappingControl}
        *
        * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
        */
        Builder setMappingcontrol(GemValue<TypeMirror> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link BeanMappingGem}
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

    private static class BuilderImpl implements Builder<BeanMappingGem> {

        private GemValue<TypeMirror> resultType;
        private GemValue<List<TypeMirror>> qualifiedBy;
        private GemValue<List<String>> qualifiedByName;
        private GemValue<String> nullValueMappingStrategy;
        private GemValue<String> nullValuePropertyMappingStrategy;
        private GemValue<String> nullValueCheckStrategy;
        private GemValue<Boolean> ignoreByDefault;
        private GemValue<List<String>> ignoreUnmappedSourceProperties;
        private GemValue<BuilderGem> builder;
        private GemValue<TypeMirror> mappingControl;
        private AnnotationMirror mirror;

        public Builder setResulttype(GemValue<TypeMirror> resultType ) {
            this.resultType = resultType;
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

        public Builder setNullvaluemappingstrategy(GemValue<String> nullValueMappingStrategy ) {
            this.nullValueMappingStrategy = nullValueMappingStrategy;
            return this;
        }

        public Builder setNullvaluepropertymappingstrategy(GemValue<String> nullValuePropertyMappingStrategy ) {
            this.nullValuePropertyMappingStrategy = nullValuePropertyMappingStrategy;
            return this;
        }

        public Builder setNullvaluecheckstrategy(GemValue<String> nullValueCheckStrategy ) {
            this.nullValueCheckStrategy = nullValueCheckStrategy;
            return this;
        }

        public Builder setIgnorebydefault(GemValue<Boolean> ignoreByDefault ) {
            this.ignoreByDefault = ignoreByDefault;
            return this;
        }

        public Builder setIgnoreunmappedsourceproperties(GemValue<List<String>> ignoreUnmappedSourceProperties ) {
            this.ignoreUnmappedSourceProperties = ignoreUnmappedSourceProperties;
            return this;
        }

        public Builder setBuilder(GemValue<BuilderGem> builder ) {
            this.builder = builder;
            return this;
        }

        public Builder setMappingcontrol(GemValue<TypeMirror> mappingControl ) {
            this.mappingControl = mappingControl;
            return this;
        }

        public Builder  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public BeanMappingGem build() {
            return new BeanMappingGem( this );
        }
    }

}
