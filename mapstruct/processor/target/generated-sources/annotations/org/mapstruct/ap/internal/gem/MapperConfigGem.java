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

public class MapperConfigGem implements Gem {

    private final GemValue<List<TypeMirror>> uses;
    private final GemValue<List<TypeMirror>> imports;
    private final GemValue<String> unmappedSourcePolicy;
    private final GemValue<String> unmappedTargetPolicy;
    private final GemValue<String> typeConversionPolicy;
    private final GemValue<String> componentModel;
    private final GemValue<String> implementationName;
    private final GemValue<String> implementationPackage;
    private final GemValue<String> collectionMappingStrategy;
    private final GemValue<String> nullValueMappingStrategy;
    private final GemValue<String> nullValuePropertyMappingStrategy;
    private final GemValue<String> mappingInheritanceStrategy;
    private final GemValue<String> nullValueCheckStrategy;
    private final GemValue<String> injectionStrategy;
    private final GemValue<Boolean> disableSubMappingMethodsGeneration;
    private final GemValue<BuilderGem> builder;
    private final GemValue<TypeMirror> mappingControl;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private MapperConfigGem( BuilderImpl builder ) {
        this.uses = builder.uses;
        this.imports = builder.imports;
        this.unmappedSourcePolicy = builder.unmappedSourcePolicy;
        this.unmappedTargetPolicy = builder.unmappedTargetPolicy;
        this.typeConversionPolicy = builder.typeConversionPolicy;
        this.componentModel = builder.componentModel;
        this.implementationName = builder.implementationName;
        this.implementationPackage = builder.implementationPackage;
        this.collectionMappingStrategy = builder.collectionMappingStrategy;
        this.nullValueMappingStrategy = builder.nullValueMappingStrategy;
        this.nullValuePropertyMappingStrategy = builder.nullValuePropertyMappingStrategy;
        this.mappingInheritanceStrategy = builder.mappingInheritanceStrategy;
        this.nullValueCheckStrategy = builder.nullValueCheckStrategy;
        this.injectionStrategy = builder.injectionStrategy;
        this.disableSubMappingMethodsGeneration = builder.disableSubMappingMethodsGeneration;
        this.builder = builder.builder;
        this.mappingControl = builder.mappingControl;
        isValid = ( this.uses != null ? this.uses.isValid() : false )
               && ( this.imports != null ? this.imports.isValid() : false )
               && ( this.unmappedSourcePolicy != null ? this.unmappedSourcePolicy.isValid() : false )
               && ( this.unmappedTargetPolicy != null ? this.unmappedTargetPolicy.isValid() : false )
               && ( this.typeConversionPolicy != null ? this.typeConversionPolicy.isValid() : false )
               && ( this.componentModel != null ? this.componentModel.isValid() : false )
               && ( this.implementationName != null ? this.implementationName.isValid() : false )
               && ( this.implementationPackage != null ? this.implementationPackage.isValid() : false )
               && ( this.collectionMappingStrategy != null ? this.collectionMappingStrategy.isValid() : false )
               && ( this.nullValueMappingStrategy != null ? this.nullValueMappingStrategy.isValid() : false )
               && ( this.nullValuePropertyMappingStrategy != null ? this.nullValuePropertyMappingStrategy.isValid() : false )
               && ( this.mappingInheritanceStrategy != null ? this.mappingInheritanceStrategy.isValid() : false )
               && ( this.nullValueCheckStrategy != null ? this.nullValueCheckStrategy.isValid() : false )
               && ( this.injectionStrategy != null ? this.injectionStrategy.isValid() : false )
               && ( this.disableSubMappingMethodsGeneration != null ? this.disableSubMappingMethodsGeneration.isValid() : false )
               && ( this.builder != null ? this.builder.isValid() : false )
               && ( this.mappingControl != null ? this.mappingControl.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#uses}
    */
    public GemValue<List<TypeMirror>> uses( ) {
        return uses;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#imports}
    */
    public GemValue<List<TypeMirror>> imports( ) {
        return imports;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#unmappedSourcePolicy}
    */
    public GemValue<String> unmappedSourcePolicy( ) {
        return unmappedSourcePolicy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#unmappedTargetPolicy}
    */
    public GemValue<String> unmappedTargetPolicy( ) {
        return unmappedTargetPolicy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#typeConversionPolicy}
    */
    public GemValue<String> typeConversionPolicy( ) {
        return typeConversionPolicy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#componentModel}
    */
    public GemValue<String> componentModel( ) {
        return componentModel;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#implementationName}
    */
    public GemValue<String> implementationName( ) {
        return implementationName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#implementationPackage}
    */
    public GemValue<String> implementationPackage( ) {
        return implementationPackage;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#collectionMappingStrategy}
    */
    public GemValue<String> collectionMappingStrategy( ) {
        return collectionMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#nullValueMappingStrategy}
    */
    public GemValue<String> nullValueMappingStrategy( ) {
        return nullValueMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#nullValuePropertyMappingStrategy}
    */
    public GemValue<String> nullValuePropertyMappingStrategy( ) {
        return nullValuePropertyMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#mappingInheritanceStrategy}
    */
    public GemValue<String> mappingInheritanceStrategy( ) {
        return mappingInheritanceStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#nullValueCheckStrategy}
    */
    public GemValue<String> nullValueCheckStrategy( ) {
        return nullValueCheckStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#injectionStrategy}
    */
    public GemValue<String> injectionStrategy( ) {
        return injectionStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#disableSubMappingMethodsGeneration}
    */
    public GemValue<Boolean> disableSubMappingMethodsGeneration( ) {
        return disableSubMappingMethodsGeneration;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#builder}
    */
    public GemValue<BuilderGem> builder( ) {
        return builder;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapperConfigGem#mappingControl}
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

    public static MapperConfigGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static MapperConfigGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.MapperConfig".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "uses".equals( methodName ) ) {
                builder.setUses( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "imports".equals( methodName ) ) {
                builder.setImports( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "unmappedSourcePolicy".equals( methodName ) ) {
                builder.setUnmappedsourcepolicy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "unmappedTargetPolicy".equals( methodName ) ) {
                builder.setUnmappedtargetpolicy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "typeConversionPolicy".equals( methodName ) ) {
                builder.setTypeconversionpolicy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "componentModel".equals( methodName ) ) {
                builder.setComponentmodel( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "implementationName".equals( methodName ) ) {
                builder.setImplementationname( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "implementationPackage".equals( methodName ) ) {
                builder.setImplementationpackage( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "collectionMappingStrategy".equals( methodName ) ) {
                builder.setCollectionmappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "nullValueMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluemappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "nullValuePropertyMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluepropertymappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "mappingInheritanceStrategy".equals( methodName ) ) {
                builder.setMappinginheritancestrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "nullValueCheckStrategy".equals( methodName ) ) {
                builder.setNullvaluecheckstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "injectionStrategy".equals( methodName ) ) {
                builder.setInjectionstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "disableSubMappingMethodsGeneration".equals( methodName ) ) {
                builder.setDisablesubmappingmethodsgeneration( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), Boolean.class ) );
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
        * Sets the {@link GemValue} for {@link MapperConfigGem#uses}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setUses(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#imports}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setImports(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#unmappedSourcePolicy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setUnmappedsourcepolicy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#unmappedTargetPolicy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setUnmappedtargetpolicy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#typeConversionPolicy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setTypeconversionpolicy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#componentModel}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setComponentmodel(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#implementationName}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setImplementationname(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#implementationPackage}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setImplementationpackage(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#collectionMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setCollectionmappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#nullValueMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setNullvaluemappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#nullValuePropertyMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setNullvaluepropertymappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#mappingInheritanceStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setMappinginheritancestrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#nullValueCheckStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setNullvaluecheckstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#injectionStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setInjectionstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#disableSubMappingMethodsGeneration}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setDisablesubmappingmethodsgeneration(GemValue<Boolean> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#builder}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setBuilder(GemValue<BuilderGem> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapperConfigGem#mappingControl}
        *
        * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
        */
        Builder setMappingcontrol(GemValue<TypeMirror> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link MapperConfigGem}
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

    private static class BuilderImpl implements Builder<MapperConfigGem> {

        private GemValue<List<TypeMirror>> uses;
        private GemValue<List<TypeMirror>> imports;
        private GemValue<String> unmappedSourcePolicy;
        private GemValue<String> unmappedTargetPolicy;
        private GemValue<String> typeConversionPolicy;
        private GemValue<String> componentModel;
        private GemValue<String> implementationName;
        private GemValue<String> implementationPackage;
        private GemValue<String> collectionMappingStrategy;
        private GemValue<String> nullValueMappingStrategy;
        private GemValue<String> nullValuePropertyMappingStrategy;
        private GemValue<String> mappingInheritanceStrategy;
        private GemValue<String> nullValueCheckStrategy;
        private GemValue<String> injectionStrategy;
        private GemValue<Boolean> disableSubMappingMethodsGeneration;
        private GemValue<BuilderGem> builder;
        private GemValue<TypeMirror> mappingControl;
        private AnnotationMirror mirror;

        public Builder setUses(GemValue<List<TypeMirror>> uses ) {
            this.uses = uses;
            return this;
        }

        public Builder setImports(GemValue<List<TypeMirror>> imports ) {
            this.imports = imports;
            return this;
        }

        public Builder setUnmappedsourcepolicy(GemValue<String> unmappedSourcePolicy ) {
            this.unmappedSourcePolicy = unmappedSourcePolicy;
            return this;
        }

        public Builder setUnmappedtargetpolicy(GemValue<String> unmappedTargetPolicy ) {
            this.unmappedTargetPolicy = unmappedTargetPolicy;
            return this;
        }

        public Builder setTypeconversionpolicy(GemValue<String> typeConversionPolicy ) {
            this.typeConversionPolicy = typeConversionPolicy;
            return this;
        }

        public Builder setComponentmodel(GemValue<String> componentModel ) {
            this.componentModel = componentModel;
            return this;
        }

        public Builder setImplementationname(GemValue<String> implementationName ) {
            this.implementationName = implementationName;
            return this;
        }

        public Builder setImplementationpackage(GemValue<String> implementationPackage ) {
            this.implementationPackage = implementationPackage;
            return this;
        }

        public Builder setCollectionmappingstrategy(GemValue<String> collectionMappingStrategy ) {
            this.collectionMappingStrategy = collectionMappingStrategy;
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

        public Builder setMappinginheritancestrategy(GemValue<String> mappingInheritanceStrategy ) {
            this.mappingInheritanceStrategy = mappingInheritanceStrategy;
            return this;
        }

        public Builder setNullvaluecheckstrategy(GemValue<String> nullValueCheckStrategy ) {
            this.nullValueCheckStrategy = nullValueCheckStrategy;
            return this;
        }

        public Builder setInjectionstrategy(GemValue<String> injectionStrategy ) {
            this.injectionStrategy = injectionStrategy;
            return this;
        }

        public Builder setDisablesubmappingmethodsgeneration(GemValue<Boolean> disableSubMappingMethodsGeneration ) {
            this.disableSubMappingMethodsGeneration = disableSubMappingMethodsGeneration;
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

        public MapperConfigGem build() {
            return new MapperConfigGem( this );
        }
    }

}
