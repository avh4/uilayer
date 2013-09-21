package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.mvc.Ref;

public class AlarmCommands {
   private final Ref<AlarmModel> ref;

   public AlarmCommands(AlarmModelChannel ref) {
       this.ref = ref;
   }

   public void increment() {
       AlarmModel newValue = ref.get().increment();
       ref.swap(ref.get(), newValue);
   }
}
