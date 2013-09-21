package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.framework.uilayer.mvc.ChannelTransformation;

public class AlarmViewChannel extends ChannelTransformation<AlarmModel, AlarmView> {
    public AlarmViewChannel(Channel<AlarmModel> dataModel, AlarmConfig config) {
        super(dataModel, new AlarmView(config.startHue(), config.endHue()));
    }
}
