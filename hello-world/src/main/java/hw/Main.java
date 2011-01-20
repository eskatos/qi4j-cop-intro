package hw;

import org.qi4j.api.composite.TransientBuilderFactory;
import org.qi4j.api.composite.TransientComposite;

import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

public class Main
{

    // ------------------------------------------------------------------------
    public static interface HelloWorld
    {

        String sayHello();

    }

    // ------------------------------------------------------------------------
    public static class HelloWorldImpl
            implements HelloWorld
    {

        @Override
        public String sayHello()
        {
            return "Hello Qi4j World !";
        }

    }

    // ------------------------------------------------------------------------
    public static interface HelloWorldComposite
            extends HelloWorld, TransientComposite
    {
    }

    // ------------------------------------------------------------------------
    public static void main( String[] args )
    {
        SingletonAssembler assembler = new SingletonAssembler()
        {

            @Override
            public void assemble( ModuleAssembly moduleAssembly )
                    throws AssemblyException
            {
                moduleAssembly.addTransients( HelloWorldComposite.class ).withMixins( HelloWorldImpl.class );
            }

        };

        TransientBuilderFactory factory = assembler.transientBuilderFactory();

        HelloWorld hw = factory.newTransient( HelloWorld.class );

        System.out.println( hw.sayHello() );
    }

}
