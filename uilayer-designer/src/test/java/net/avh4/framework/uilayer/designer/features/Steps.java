package net.avh4.framework.uilayer.designer.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.test.ClasspathResourcesExternalStorage;
import net.avh4.framework.uilayer.designer.Designer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static org.fest.assertions.Assertions.assertThat;

@SuppressWarnings("UnusedDeclaration")
public class Steps {
    private final Agent agent;
    private final ExternalStorage externalStorage;

    public Steps() {
        this.externalStorage = new ClasspathResourcesExternalStorage(".");
        Designer designer = new Designer(this.externalStorage);
        this.agent = new Agent(designer);
    }

    @Given("^a new mockup$")
    public void a_new_mockup() throws Throwable {
    }

    @When("^I add placeholders$")
    public void I_add_placeholders() throws Throwable {
        agent.addPlaceholder(5, 5, 795, 595);
        agent.addPlaceholder(10, 15, 30, 35);
    }

    @When("^I save the mockup$")
    public void save_the_mockup() throws Throwable {
        agent.saveMockup("MyScene.java");
    }

    @Then("^I have java code that represents the mockup I designed$")
    public void I_have_java_code_that_represents_the_mockup_I_designed() throws Throwable {
        assertThat(externalStorage.getFile("MyScene.java").getContents())
                .isEqualTo(example("SceneWithTwoPlaceholders.java-txt"));
    }

    @Then("^I have java code that represents an empty scene$")
    public void I_have_java_code_that_represents_an_empty_scene() throws Throwable {
        assertThat(externalStorage.getFile("MyScene.java").getContents())
                .isEqualTo(example("EmptyScene.java-txt"));
    }

    private String example(String exampleName) throws IOException {
        InputStream stream = getClass().getResourceAsStream(exampleName);
        return IOUtils.toString(stream);
    }
}
