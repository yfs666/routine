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

public class MappingGem implements Gem {

    private final GemValue<String> target;
    private final GemValue<String> source;
    private final GemValue<String> dateFormat;
    private final GemValue<String> numberFormat;
    private final GemValue<String> constant;
    private final GemValue<String> expression;
    private final GemValue<String> defaultExpression;
    private final GemValue<Boolean> ignore;
    private final GemValue<List<TypeMirror>> qualifiedBy;
    private final GemValue<List<String>> qualifiedByName;
    private final GemValue<TypeMirror> resultType;
    private final GemValue<List<String>> dependsOn;
    private final GemValue<String> defaultValue;
    private final GemValue<String> nullValueCheckStrategy;
    private final GemValue<String> nullValuePropertyMappingStrategy;
    private final GemValue<TypeMirror> mappingControl;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private MappingGem( BuilderImpl builder ) {
        this.target = builder.target;
        this.source = builder.source;
        this.dateFormat = builder.dateFormat;
        this.numberFormat = builder.numberFormat;
        this.constant = builder.constant;
        this.expression = builder.expression;
        this.defaultExpression = builder.defaultExpression;
        this.ignore = builder.ignore;
        this.qualifiedBy = builder.qualifiedBy;
        this.qualifiedByName = builder.qualifiedByName;
        this.resultType = builder.resultType;
        this.dependsOn = builder.dependsOn;
        this.defaultValue = builder.defaultValue;
        this.nullValueCheckStrategy = builder.nullValueCheckStrategy;
        this.nullValuePropertyMappingStrategy = builder.nullValuePropertyMappingStrategy;
        this.mappingControl = builder.mappingControl;
        isValid = ( this.target != null ? this.target.isValid() : false )
               && ( this.source != null ? this.source.isValid() : false )
               && ( this.dateFormat != null ? this.dateFormat.isValid() : false )
               && ( this.numberFormat != null ? this.numberFormat.isValid() : false )
               && ( this.constant != null ? this.constant.isValid() : false )
               && ( this.expression != null ? this.expression.isValid() : false )
               && ( this.defaultExpression != null ? this.defaultExpression.isValid() : false )
               && ( this.ignore != null ? this.ignore.isValid() : false )
               && ( this.qualifiedBy != null ? this.qualifiedBy.isValid() : false )
               && ( this.qualifiedByName != null ? this.qualifiedByName.isValid() : false )
               && ( this.resultType != null ? this.resultType.isValid() : false )
               && ( this.dependsOn != null ? this.dependsOn.isValid() : false )
               && ( this.defaultValue != null ? this.defaultValue.isValid() : false )
               && ( this.nullValueCheckStrategy != null ? this.nullValueCheckStrategy.isValid() : false )
               && ( this.nullValuePropertyMappingStrategy != null ? this.nullValuePropertyMappingStrategy.isValid() : false )
               && ( this.mappingControl != null ? this.mappingControl.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#target}
    */
    public GemValue<String> target( ) {
        return target;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#source}
    */
    public GemValue<String> source( ) {
        return source;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#dateFormat}
    */
    public GemValue<String> dateFormat( ) {
        return dateFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#numberFormat}
    */
    public GemValue<String> numberFormat( ) {
        return numberFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#constant}
    */
    public GemValue<String> constant( ) {
        return constant;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#expression}
    */
    public GemValue<String> expression( ) {
        return expression;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#defaultExpression}
    */
    public GemValue<String> defaultExpression( ) {
        return defaultExpression;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#ignore}
    */
    public GemValue<Boolean> ignore( ) {
        return ignore;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#qualifiedBy}
    */
    public GemValue<List<TypeMirror>> qualifiedBy( ) {
        return qualifiedBy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#qualifiedByName}
    */
    public GemValue<List<String>> qualifiedByName( ) {
        return qualifiedByName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#resultType}
    */
    public GemValue<TypeMirror> resultType( ) {
        return resultType;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#dependsOn}
    */
    public GemValue<List<String>> dependsOn( ) {
        return dependsOn;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#defaultValue}
    */
    public GemValue<String> defaultValue( ) {
        return defaultValue;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#nullValueCheckStrategy}
    */
    public GemValue<String> nullValueCheckStrategy( ) {
        return nullValueCheckStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#nullValuePropertyMappingStrategy}
    */
    public GemValue<String> nullValuePropertyMappingStrategy( ) {
        return nullValuePropertyMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MappingGem#mappingControl}
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

    public static MappingGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static MappingGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.Mapping".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "target".equals( methodName ) ) {
                builder.setTarget( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "source".equals( methodName ) ) {
                builder.setSource( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "dateFormat".equals( methodName ) ) {
                builder.setDateformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "numberFormat".equals( methodName ) ) {
                builder.setNumberformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "constant".equals( methodName ) ) {
                builder.setConstant( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "expression".equals( methodName ) ) {
                builder.setExpression( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "defaultExpression".equals( methodName ) ) {
                builder.setDefaultexpression( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "ignore".equals( methodName ) ) {
                builder.setIgnore( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), Boolean.class ) );
            }
            else if ( "qualifiedBy".equals( methodName ) ) {
                builder.setQualifiedby( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "qualifiedByName".equals( methodName ) ) {
                builder.setQualifiedbyname( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "resultType".equals( methodName ) ) {
                builder.setResulttype( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "dependsOn".equals( methodName ) ) {
                builder.setDependson( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "defaultValue".equals( methodName ) ) {
                builder.setDefaultvalue( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "nullValueCheckStrategy".equals( methodName ) ) {
                builder.setNullvaluecheckstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "nullValuePropertyMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluepropertymappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
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
        * Sets the {@link GemValue} for {@link MappingGem#target}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setTarget(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#source}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setSource(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#dateFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setDateformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#numberFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setNumberformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#constant}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setConstant(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#expression}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setExpression(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#defaultExpression}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setDefaultexpression(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#ignore}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setIgnore(GemValue<Boolean> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#qualifiedBy}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setQualifiedby(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#qualifiedByName}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setQualifiedbyname(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#resultType}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setResulttype(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#dependsOn}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setDependson(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#defaultValue}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setDefaultvalue(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#nullValueCheckStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setNullvaluecheckstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#nullValuePropertyMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setNullvaluepropertymappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MappingGem#mappingControl}
        *
        * @return the {@link Builder} for this gem, representing {@link MappingGem}
        */
        Builder setMappingcontrol(GemValue<TypeMirror> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link MappingGem}
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

    private static class BuilderImpl implements Builder<MappingGem> {

        private GemValue<String> target;
        private GemValue<String> source;
        private GemValue<String> dateFormat;
        private GemValue<String> numberFormat;
        private GemValue<String> constant;
        private GemValue<String> expression;
        private GemValue<String> defaultExpression;
        private GemValue<Boolean> ignore;
        private GemValue<List<TypeMirror>> qualifiedBy;
        private GemValue<List<String>> qualifiedByName;
        private GemValue<TypeMirror> resultType;
        private GemValue<List<String>> dependsOn;
        private GemValue<String> defaultValue;
        private GemValue<String> nullValueCheckStrategy;
        private GemValue<String> nullValuePropertyMappingStrategy;
        private GemValue<TypeMirror> mappingControl;
        private AnnotationMirror mirror;

        public Builder setTarget(GemValue<String> target ) {
            this.target = target;
            return this;
        }

        public Builder setSource(GemValue<String> source ) {
            this.source = source;
            return this;
        }

        public Builder setDateformat(GemValue<String> dateFormat ) {
            this.dateFormat = dateFormat;
            return this;
        }

        public Builder setNumberformat(GemValue<String> numberFormat ) {
            this.numberFormat = numberFormat;
            return this;
        }

        public Builder setConstant(GemValue<String> constant ) {
            this.constant = constant;
            return this;
        }

        public Builder setExpression(GemValue<String> expression ) {
            this.expression = expression;
            return this;
        }

        public Builder setDefaultexpression(GemValue<String> defaultExpression ) {
            this.defaultExpression = defaultExpression;
            return this;
        }

        public Builder setIgnore(GemValue<Boolean> ignore ) {
            this.ignore = ignore;
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

        public Builder setResulttype(GemValue<TypeMirror> resultType ) {
            this.resultType = resultType;
            return this;
        }

        public Builder setDependson(GemValue<List<String>> dependsOn ) {
            this.dependsOn = dependsOn;
            return this;
        }

        public Builder setDefaultvalue(GemValue<String> defaultValue ) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder setNullvaluecheckstrategy(GemValue<String> nullValueCheckStrategy ) {
            this.nullValueCheckStrategy = nullValueCheckStrategy;
            return this;
        }

        public Builder setNullvaluepropertymappingstrategy(GemValue<String> nullValuePropertyMappingStrategy ) {
            this.nullValuePropertyMappingStrategy = nullValuePropertyMappingStrategy;
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

        public MappingGem build() {
            return new MappingGem( this );
        }
    }

}
