package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.mvc.Ref;

public class AlarmModelChannel extends Ref<AlarmModel> {
    public AlarmModelChannel(AlarmConfig config) {
        super(new AlarmModel(config.max()));
    }
}
