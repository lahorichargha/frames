package com.tinkerpop.frames;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory;
import com.tinkerpop.frames.domain.classes.Person;
import com.tinkerpop.frames.domain.classes.Project;
import com.tinkerpop.frames.domain.relations.Created;
import com.tinkerpop.frames.domain.relations.CreatedBy;
import junit.framework.TestCase;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ElementTest extends TestCase {

    public void testGettingProperties() {
        Graph graph = TinkerGraphFactory.createTinkerGraph();
        FramesManager manager = new FramesManager(graph);

        Person marko = manager.frame(graph.getVertex(1), Person.class);
        assertEquals(marko.getName(), "marko");
        assertEquals(marko.getAge(), new Integer(29));

        Project lop = manager.frame(graph.getVertex(3), Project.class);
        assertEquals(lop.getName(), "lop");
        assertEquals(lop.getLanguage(), "java");

        Created markoCreatedLop = manager.frame(graph.getEdge(9), Created.class, Relation.Direction.STANDARD);
        assertEquals(markoCreatedLop.getWeight(), 0.4f);

        CreatedBy lopCreatedByMarko = manager.frame(graph.getEdge(9), CreatedBy.class, Relation.Direction.INVERSE);
        assertEquals(lopCreatedByMarko.getWeight(), 0.4f);
    }
}
