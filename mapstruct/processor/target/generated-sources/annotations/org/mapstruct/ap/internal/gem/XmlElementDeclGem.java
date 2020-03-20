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

public class XmlElementDeclGem implements Gem {

    private final GemValue<TypeMirror> scope;
    private final GemValue<String> namespace;
    private final GemValue<String> name;
    private final GemValue<String> substitutionHeadNamespace;
    private final GemValue<String> substitutionHeadName;
    private final GemValue<String> defaultValue;
    private final boolean isValid;
    private final AnnotationMirror mirror;

    private XmlElementDeclGem( BuilderImpl builder ) {
        this.scope = builder.scope;
        this.namespace = builder.namespace;
        this.name = builder.name;
        this.substitutionHeadNamespace = builder.substitutionHeadNamespace;
        this.substitutionHeadName = builder.substitutionHeadName;
        this.defaultValue = builder.defaultValue;
        isValid = ( this.scope != null ? this.scope.isValid() : false )
               && ( this.namespace != null ? this.namespace.isValid() : false )
               && ( this.name != null ? this.name.isValid() : false )
               && ( this.substitutionHeadNamespace != null ? this.substitutionHeadNamespace.isValid() : false )
               && ( this.substitutionHeadName != null ? this.substitutionHeadName.isValid() : false )
               && ( this.defaultValue != null ? this.defaultValue.isValid() : false );
        mirror = builder.mirror;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link XmlElementDeclGem#scope}
    */
    public GemValue<TypeMirror> scope( ) {
        return scope;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link XmlElementDeclGem#namespace}
    */
    public GemValue<String> namespace( ) {
        return namespace;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link XmlElementDeclGem#name}
    */
    public GemValue<String> name( ) {
        return name;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link XmlElementDeclGem#substitutionHeadNamespace}
    */
    public GemValue<String> substitutionHeadNamespace( ) {
        return substitutionHeadNamespace;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link XmlElementDeclGem#substitutionHeadName}
    */
    public GemValue<String> substitutionHeadName( ) {
        return substitutionHeadName;
    }

    /**
    * accessor
    *
    * @return the {@link GemValue} for {@link XmlElementDeclGem#defaultValue}
    */
    public GemValue<String> defaultValue( ) {
        return defaultValue;
    }

    @Override
    public AnnotationMirror mirror( ) {
        return mirror;
    }

    @Override
    public boolean isValid( ) {
        return isValid;
    }

    public static XmlElementDeclGem  instanceOn(Element element) {
        return build( element, new BuilderImpl() );
    }

    public static XmlElementDeclGem instanceOn(AnnotationMirror mirror ) {
        return build( mirror, new BuilderImpl() );
    }

    public static  <T> T  build(Element element, Builder<T> builder) {
        AnnotationMirror mirror = element.getAnnotationMirrors().stream()
            .filter( a ->  "javax.xml.bind.annotation.XmlElementDecl".contentEquals( ( ( TypeElement )a.getAnnotationType().asElement() ).getQualifiedName() ) )
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

            if ( "scope".equals( methodName ) ) {
                builder.setScope( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), TypeMirror.class ) );
            }
            else if ( "namespace".equals( methodName ) ) {
                builder.setNamespace( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "name".equals( methodName ) ) {
                builder.setName( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "substitutionHeadNamespace".equals( methodName ) ) {
                builder.setSubstitutionheadnamespace( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "substitutionHeadName".equals( methodName ) ) {
                builder.setSubstitutionheadname( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
            }
            else if ( "defaultValue".equals( methodName ) ) {
                builder.setDefaultvalue( GemValue.create( values.get( methodName ), defaultValues.get( methodName ), String.class ) );
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
        * Sets the {@link GemValue} for {@link XmlElementDeclGem#scope}
        *
        * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
        */
        Builder setScope(GemValue<TypeMirror> methodName );

       /**
        * Sets the {@link GemValue} for {@link XmlElementDeclGem#namespace}
        *
        * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
        */
        Builder setNamespace(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link XmlElementDeclGem#name}
        *
        * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
        */
        Builder setName(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link XmlElementDeclGem#substitutionHeadNamespace}
        *
        * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
        */
        Builder setSubstitutionheadnamespace(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link XmlElementDeclGem#substitutionHeadName}
        *
        * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
        */
        Builder setSubstitutionheadname(GemValue<String> methodName );

       /**
        * Sets the {@link GemValue} for {@link XmlElementDeclGem#defaultValue}
        *
        * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
        */
        Builder setDefaultvalue(GemValue<String> methodName );

        /**
         * Sets the annotation mirror
         *
         * @param mirror the mirror which this gem represents
         *
         * @return the {@link Builder} for this gem, representing {@link XmlElementDeclGem}
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

    private static class BuilderImpl implements Builder<XmlElementDeclGem> {

        private GemValue<TypeMirror> scope;
        private GemValue<String> namespace;
        private GemValue<String> name;
        private GemValue<String> substitutionHeadNamespace;
        private GemValue<String> substitutionHeadName;
        private GemValue<String> defaultValue;
        private AnnotationMirror mirror;

        public Builder setScope(GemValue<TypeMirror> scope ) {
            this.scope = scope;
            return this;
        }

        public Builder setNamespace(GemValue<String> namespace ) {
            this.namespace = namespace;
            return this;
        }

        public Builder setName(GemValue<String> name ) {
            this.name = name;
            return this;
        }

        public Builder setSubstitutionheadnamespace(GemValue<String> substitutionHeadNamespace ) {
            this.substitutionHeadNamespace = substitutionHeadNamespace;
            return this;
        }

        public Builder setSubstitutionheadname(GemValue<String> substitutionHeadName ) {
            this.substitutionHeadName = substitutionHeadName;
            return this;
        }

        public Builder setDefaultvalue(GemValue<String> defaultValue ) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder  setMirror( AnnotationMirror mirror ) {
            this.mirror = mirror;
            return this;
        }

        public XmlElementDeclGem build() {
            return new XmlElementDeclGem( this );
        }
    }

}
