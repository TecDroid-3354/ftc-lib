package net.tecdroid.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import net.tecdroid.ftc.command.CommandScheduler;
import net.tecdroid.ftc.command.TriggeredCommandCycle;
import net.tecdroid.ftc.input.CommandGamepad;

public abstract class OperationMode extends OpMode {

    CommandScheduler scheduler = new CommandScheduler();
    TriggeredCommandCycle triggeredCommandCycle = new TriggeredCommandCycle();
    CommandGamepad controllerOne = null;
    CommandGamepad controllerTwo = null;

    public abstract void onInit();
    public abstract void onInitLoop();
    public abstract void onStart();
    public abstract void onMainLoop();
    public abstract void onStop();
    public abstract void onGlobalStart();
    public abstract void onGlobalLoop();

    private void beginCycle() {
        triggeredCommandCycle.update(scheduler);
        controllerOne.update();
        controllerTwo.update();
    }

    private void endCycle() {
        scheduler.runScheduledCommands();
    }

    @Override
    public void init() {
        controllerOne = new CommandGamepad(gamepad1);
        controllerTwo = new CommandGamepad(gamepad2);
        onGlobalStart();
        onInit();
    }

    @Override
    public void init_loop() {
        super.init_loop();
        beginCycle();
        onGlobalLoop();
        onInitLoop();
        endCycle();
    }

    @Override
    public void start() {
        super.start();
        onGlobalStart();
        onStart();
    }

    @Override
    public void loop() {
        beginCycle();
        onGlobalLoop();
        onMainLoop();
        endCycle();
    }

    @Override
    public void stop() {
        super.stop();
        onStop();
    }
}
