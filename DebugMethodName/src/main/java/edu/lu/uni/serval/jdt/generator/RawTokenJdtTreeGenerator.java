package edu.lu.uni.serval.jdt.generator;

import edu.lu.uni.serval.jdt.visitor.AbstractRawTokenJdtVisitor;
import edu.lu.uni.serval.jdt.visitor.RawTokenJdtVisitor;

@Register(id = "java-jdt", accept = "\\.java$", priority = Registry.Priority.MAXIMUM)
public class RawTokenJdtTreeGenerator extends AbstractRawTokenJdtTreeGenerator {

    @Override
    protected AbstractRawTokenJdtVisitor createVisitor() {
        return new RawTokenJdtVisitor();
    }

}
