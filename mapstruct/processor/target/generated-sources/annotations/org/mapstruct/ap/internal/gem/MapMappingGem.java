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

public class MapMappingGem implements Gem {

    private final GemValue<String> keyDateFormat;
    private final GemValue<String> valueDateFormat;
    private final GemValue<String> keyNumberFormat;
    private final GemValue<String> valueNumberFormat;
    private final GemValue<List<TypeMirror>> keyQualifiedBy;
    private final GemValue<List<String>> keyQualifiedByName;
    private final GemValue<List<TypeMirror>> valueQualifiedBy;
    private final GemValue<List<String>> valueQualifiedByName;
    private final GemValue<TypeMirror> keyTargetType;
    private final GemValue<TypeMirror> valueTargetType;
    private final GemValue<String> nullValueMappingStrategy;
    private final GemValue<TypeMirror> keyMappingControl;
    private final GemValue<TypeMirror> valueMappingControl;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private MapMappingGem( BuilderImpl builder ) {
        this.keyDateFormat = builder.keyDateFormat;
        this.valueDateFormat = builder.valueDateFormat;
        this.keyNumberFormat = builder.keyNumberFormat;
        this.valueNumberFormat = builder.valueNumberFormat;
        this.keyQualifiedBy = builder.keyQualifiedBy;
        this.keyQualifiedByName = builder.keyQualifiedByName;
        this.valueQualifiedBy = builder.valueQualifiedBy;
        this.valueQualifiedByName = builder.valueQualifiedByName;
        this.keyTargetType = builder.keyTargetType;
        this.valueTargetType = builder.valueTargetType;
        this.nullValueMappingStrategy = builder.nullValueMappingStrategy;
        this.keyMappingControl = builder.keyMappingControl;
        this.valueMappingControl = builder.valueMappingControl;
        isValid = ( this.keyDateFormat != null ? this.keyDateFormat.isValid() : false )
               && ( this.valueDateFormat != null ? this.valueDateFormat.isValid() : false )
               && ( this.keyNumberFormat != null ? this.keyNumberFormat.isValid() : false )
               && ( this.valueNumberFormat != null ? this.valueNumberFormat.isValid() : false )
               && ( this.keyQualifiedBy != null ? this.keyQualifiedBy.isValid() : false )
               && ( this.keyQualifiedByName != null ? this.keyQualifiedByName.isValid() : false )
               && ( this.valueQualifiedBy != null ? this.valueQualifiedBy.isValid() : false )
               && ( this.valueQualifiedByName != null ? this.valueQualifiedByName.isValid() : false )
               && ( this.keyTargetType != null ? this.keyTargetType.isValid() : false )
               && ( this.valueTargetType != null ? this.valueTargetType.isValid() : false )
               && ( this.nullValueMappingStrategy != null ? this.nullValueMappingStrategy.isValid() : false )
               && ( this.keyMappingControl != null ? this.keyMappingControl.isValid() : false )
               && ( this.valueMappingControl != null ? this.valueMappingControl.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#keyDateFormat}
    */
    public GemValue<String> keyDateFormat( ) {
        return keyDateFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#valueDateFormat}
    */
    public GemValue<String> valueDateFormat( ) {
        return valueDateFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#keyNumberFormat}
    */
    public GemValue<String> keyNumberFormat( ) {
        return keyNumberFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#valueNumberFormat}
    */
    public GemValue<String> valueNumberFormat( ) {
        return valueNumberFormat;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#keyQualifiedBy}
    */
    public GemValue<List<TypeMirror>> keyQualifiedBy( ) {
        return keyQualifiedBy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#keyQualifiedByName}
    */
    public GemValue<List<String>> keyQualifiedByName( ) {
        return keyQualifiedByName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#valueQualifiedBy}
    */
    public GemValue<List<TypeMirror>> valueQualifiedBy( ) {
        return valueQualifiedBy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#valueQualifiedByName}
    */
    public GemValue<List<String>> valueQualifiedByName( ) {
        return valueQualifiedByName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#keyTargetType}
    */
    public GemValue<TypeMirror> keyTargetType( ) {
        return keyTargetType;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#valueTargetType}
    */
    public GemValue<TypeMirror> valueTargetType( ) {
        return valueTargetType;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#nullValueMappingStrategy}
    */
    public GemValue<String> nullValueMappingStrategy( ) {
        return nullValueMappingStrategy;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#keyMappingControl}
    */
    public GemValue<TypeMirror> keyMappingControl( ) {
        return keyMappingControl;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link MapMappingGem#valueMappingControl}
    */
    public GemValue<TypeMirror> valueMappingControl( ) {
        return valueMappingControl;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static MapMappingGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static MapMappingGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.MapMapping".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "keyDateFormat".equals( methodName ) ) {
                builder.setKeydateformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "valueDateFormat".equals( methodName ) ) {
                builder.setValuedateformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "keyNumberFormat".equals( methodName ) ) {
                builder.setKeynumberformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "valueNumberFormat".equals( methodName ) ) {
                builder.setValuenumberformat( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "keyQualifiedBy".equals( methodName ) ) {
                builder.setKeyqualifiedby( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "keyQualifiedByName".equals( methodName ) ) {
                builder.setKeyqualifiedbyname( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "valueQualifiedBy".equals( methodName ) ) {
                builder.setValuequalifiedby( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "valueQualifiedByName".equals( methodName ) ) {
                builder.setValuequalifiedbyname( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "keyTargetType".equals( methodName ) ) {
                builder.setKeytargettype( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "valueTargetType".equals( methodName ) ) {
                builder.setValuetargettype( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "nullValueMappingStrategy".equals( methodName ) ) {
                builder.setNullvaluemappingstrategy( GemValue.createEnum( values.get( methodName ), defaultValues.get( methodName ) ) );
            }
            else if ( "keyMappingControl".equals( methodName ) ) {
                builder.setKeymappingcontrol( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "valueMappingControl".equals( methodName ) ) {
                builder.setValuemappingcontrol( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
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
        * Sets the {@link GemValue} for {@link MapMappingGem#keyDateFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setKeydateformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#valueDateFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setValuedateformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#keyNumberFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setKeynumberformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#valueNumberFormat}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setValuenumberformat(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#keyQualifiedBy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setKeyqualifiedby(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#keyQualifiedByName}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setKeyqualifiedbyname(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#valueQualifiedBy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setValuequalifiedby(GemValue<List<TypeMirror>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#valueQualifiedByName}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setValuequalifiedbyname(GemValue<List<String>> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#keyTargetType}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setKeytargettype(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#valueTargetType}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setValuetargettype(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#nullValueMappingStrategy}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setNullvaluemappingstrategy(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#keyMappingControl}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setKeymappingcontrol(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link MapMappingGem#valueMappingControl}
        *
        * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
        */
        Builder setValuemappingcontrol(GemValue<TypeMirror> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link MapMappingGem}
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

    private static class BuilderImpl implements Builder<MapMappingGem> {

        private GemValue<String> keyDateFormat;
        private GemValue<String> valueDateFormat;
        private GemValue<String> keyNumberFormat;
        private GemValue<String> valueNumberFormat;
        private GemValue<List<TypeMirror>> keyQualifiedBy;
        private GemValue<List<String>> keyQualifiedByName;
        private GemValue<List<TypeMirror>> valueQualifiedBy;
        private GemValue<List<String>> valueQualifiedByName;
        private GemValue<TypeMirror> keyTargetType;
        private GemValue<TypeMirror> valueTargetType;
        private GemValue<String> nullValueMappingStrategy;
        private GemValue<TypeMirror> keyMappingControl;
        private GemValue<TypeMirror> valueMappingControl;
        private AnnotationMirror mirror;

        public Builder setKeydateformat(GemValue<String> keyDateFormat ) {
            this.keyDateFormat = keyDateFormat;
            return this;
        }

        public Builder setValuedateformat(GemValue<String> valueDateFormat ) {
            this.valueDateFormat = valueDateFormat;
            return this;
        }

        public Builder setKeynumberformat(GemValue<String> keyNumberFormat ) {
            this.keyNumberFormat = keyNumberFormat;
            return this;
        }

        public Builder setValuenumberformat(GemValue<String> valueNumberFormat ) {
            this.valueNumberFormat = valueNumberFormat;
            return this;
        }

        public Builder setKeyqualifiedby(GemValue<List<TypeMirror>> keyQualifiedBy ) {
            this.keyQualifiedBy = keyQualifiedBy;
            return this;
        }

        public Builder setKeyqualifiedbyname(GemValue<List<String>> keyQualifiedByName ) {
            this.keyQualifiedByName = keyQualifiedByName;
            return this;
        }

        public Builder setValuequalifiedby(GemValue<List<TypeMirror>> valueQualifiedBy ) {
            this.valueQualifiedBy = valueQualifiedBy;
            return this;
        }

        public Builder setValuequalifiedbyname(GemValue<List<String>> valueQualifiedByName ) {
            this.valueQualifiedByName = valueQualifiedByName;
            return this;
        }

        public Builder setKeytargettype(GemValue<TypeMirror> keyTargetType ) {
            this.keyTargetType = keyTargetType;
            return this;
        }

        public Builder setValuetargettype(GemValue<TypeMirror> valueTargetType ) {
            this.valueTargetType = valueTargetType;
            return this;
        }

        public Builder setNullvaluemappingstrategy(GemValue<String> nullValueMappingStrategy ) {
            this.nullValueMappingStrategy = nullValueMappingStrategy;
            return this;
        }

        public Builder setKeymappingcontrol(GemValue<TypeMirror> keyMappingControl ) {
            this.keyMappingControl = keyMappingControl;
            return this;
        }

        public Builder setValuemappingcontrol(GemValue<TypeMirror> valueMappingControl ) {
            this.valueMappingControl = valueMappingControl;
            return this;
        }

        public Builder  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public MapMappingGem build() {
            return new MapMappingGem( this );
        }
    }

}
