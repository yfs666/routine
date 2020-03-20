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


public class BuilderGem implements Gem {

    private final GemValue<String> buildMethod;
    private final GemValue<Boolean> disableBuilder;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private BuilderGem( BuilderImpl builder ) {
        this.buildMethod = builder.buildMethod;
        this.disableBuilder = builder.disableBuilder;
        isValid = ( this.buildMethod != null ? this.buildMethod.isValid() : false )
               && ( this.disableBuilder != null ? this.disableBuilder.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BuilderGem#buildMethod}
    */
    public GemValue<String> buildMethod( ) {
        return buildMethod;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link BuilderGem#disableBuilder}
    */
    public GemValue<Boolean> disableBuilder( ) {
        return disableBuilder;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static BuilderGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static BuilderGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder_<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "org.mapstruct.Builder".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
            .findAny()
            .orElse( null );
        return build( mirror, builder );
    }

    public static <T> T build(AnnotationMirror mirror, Builder_<T> builder ) {

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

            if ( "buildMethod".equals( methodName ) ) {
                builder.setBuildmethod( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "disableBuilder".equals( methodName ) ) {
                builder.setDisablebuilder( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), Boolean.class ) );
            }
        }
        builder.setMirror( mirror );
        return builder.build();
    }

    /**
     * A builder that can be implemented by the user to define custom logic e.g. in the
     * build method, prior to creating the annotation gem.
     */
    public interface Builder_<T> {

       /**
        * Sets the {@link GemValue} for {@link BuilderGem#buildMethod}
        *
        * @return the {@link Builder_} for this gem, representing {@link BuilderGem}
        */
        Builder_ setBuildmethod(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link BuilderGem#disableBuilder}
        *
        * @return the {@link Builder_} for this gem, representing {@link BuilderGem}
        */
        Builder_ setDisablebuilder(GemValue<Boolean> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder_} for this gem, representing {@link BuilderGem}
         */
          Builder_ setMirror( AnnotationMirror mirror );

        /**
         * The build method can be overriden in a custom custom implementation, which allows
         * the user to define his own custom validation on the annotation.
         *
         * @return the representation of the annotation
         */
        T build();
    }

    private static class BuilderImpl implements Builder_<BuilderGem> {

        private GemValue<String> buildMethod;
        private GemValue<Boolean> disableBuilder;
        private AnnotationMirror mirror;

        public Builder_ setBuildmethod(GemValue<String> buildMethod ) {
            this.buildMethod = buildMethod;
            return this;
        }

        public Builder_ setDisablebuilder(GemValue<Boolean> disableBuilder ) {
            this.disableBuilder = disableBuilder;
            return this;
        }

        public Builder_  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public BuilderGem build() {
            return new BuilderGem( this );
        }
    }

}
