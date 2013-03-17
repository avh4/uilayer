package net.avh4.framework.uilayer.mvc;

import net.avh4.framework.uilayer.scene.Scene;

public interface View<M> {
    Scene getScene(M model);
}
