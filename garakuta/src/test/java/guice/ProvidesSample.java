package guice;

import static org.assertj.core.api.Assertions.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class ProvidesSample {

    @Test
    public void test() throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {
            }

            @Provides
            @Singleton
            Foo foo() {
                return new Foo();
            }

            @Provides
            Bar bar() {
                return new Bar();
            }

            @Provides
            Baz baz(Foo foo, Bar bar) {
                return new Baz(foo, bar);
            }
        });

        assertThat(injector.getInstance(Foo.class)).isSameAs(injector.getInstance(Foo.class));
        assertThat(injector.getInstance(Bar.class)).isNotSameAs(injector.getInstance(Bar.class));
        Baz baz = injector.getInstance(Baz.class);
        assertThat(baz.foo).isSameAs(injector.getInstance(Foo.class));
        assertThat(baz.bar).isNotSameAs(injector.getInstance(Bar.class));
    }

    @Test
    public void testNamed() throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {
            }

            @Provides
            @Named("foo1")
            Foo foo1() {
                return new Foo("foo1");
            }

            @Provides
            @Named("foo2")
            Foo foo2() {
                return new Foo("foo2");
            }
        });
        Qux qux = injector.getInstance(Qux.class);
        assertThat(qux.foo1.value).isEqualTo("foo1");
        assertThat(qux.foo2.value).isEqualTo("foo2");
    }

    public static class Foo {
        public final String value;

        public Foo() {
            this("foo");
        }

        public Foo(String value) {
            this.value = value;
        }
    }

    public static class Bar {
    }

    public static class Baz {

        public final Foo foo;
        public final Bar bar;

        public Baz(Foo foo, Bar bar) {
            this.foo = foo;
            this.bar = bar;
        }
    }

    public static class Qux {
        @Inject
        @Named("foo1")
        public Foo foo1;
        @Inject
        @Named("foo2")
        public Foo foo2;
    }
}
