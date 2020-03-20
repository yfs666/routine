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


public class ValueMappingGem implements Gem {

    private final GemValue<String> source;
    private final GemValue<String> target;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private ValueMappingGem( BuilderImpl builder ) {
        this.source = builder.source;
        this.target = builder.target;
        isValid = ( this.source != null ? this.source.isValid() : false )
               && ( this.target != null ? this.target.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link ValueMappingGem#source}
    */
    public GemValue<String> source( ) {
        return source;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link ValueMappingGem#target}
    */
    public GemValue<String> target( ) {
        return target;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static ValueMappingGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static ValueMappingGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.ValueMapping".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "source".equals( methodName ) ) {
                builder.setSource( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "target".equals( methodName ) ) {
                builder.setTarget( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
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
        * Sets the {@link GemValue} for {@link ValueMappingGem#source}
        *
        * @return the {@link Builder} for this gem, representing {@link ValueMappingGem}
        */
        Builder setSource(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link ValueMappingGem#target}
        *
        * @return the {@link Builder} for this gem, representing {@link ValueMappingGem}
        */
        Builder setTarget(GemValue<String> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link ValueMappingGem}
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

    private static class BuilderImpl implements Builder<ValueMappingGem> {

        private GemValue<String> source;
        private GemValue<String> target;
        private AnnotationMirror mirror;

        public Builder setSource(GemValue<String> source ) {
            this.source = source;
            return this;
        }

        public Builder setTarget(GemValue<String> target ) {
            this.target = target;
            return this;
        }

        public Builder  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public ValueMappingGem build() {
            return new ValueMappingGem( this );
        }
    }

}
