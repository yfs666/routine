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


public class ValueMappingsGem implements Gem {

    private final GemValue<List<ValueMappingGem>> value;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private ValueMappingsGem( BuilderImpl builder ) {
        this.value = builder.value;
        isValid = ( this.value != null ? this.value.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link ValueMappingsGem#value}
    */
    public GemValue<List<ValueMappingGem>> value( ) {
        return value;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static ValueMappingsGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static ValueMappingsGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.ValueMappings".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "value".equals( methodName ) ) {
                builder.setValue( GemValue.createArray( values.get( methodName ), defaultValues.get( methodName ), ValueMappingGem::instanceOn ) );
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
        * Sets the {@link GemValue} for {@link ValueMappingsGem#value}
        *
        * @return the {@link Builder} for this gem, representing {@link ValueMappingsGem}
        */
        Builder setValue(GemValue<List<ValueMappingGem>> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link ValueMappingsGem}
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

    private static class BuilderImpl implements Builder<ValueMappingsGem> {

        private GemValue<List<ValueMappingGem>> value;
        private AnnotationMirror mirror;

        public Builder setValue(GemValue<List<ValueMappingGem>> value ) {
            this.value = value;
            return this;
        }

        public Builder  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public ValueMappingsGem build() {
            return new ValueMappingsGem( this );
        }
    }

}
