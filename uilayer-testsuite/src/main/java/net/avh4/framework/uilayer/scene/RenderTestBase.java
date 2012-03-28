package net.avh4.framework.uilayer.scene;

import junit.framework.TestCase;

public abstract class RenderTestBase extends TestCase {

    private final RenderTestConnector connector = new RenderTestConnector();

    protected Scene scene;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        scene = new Scene();
    }

    protected void assertRenderingIsApproved() {
        connector.assertRenderingIsApproved(scene);
    }
}
