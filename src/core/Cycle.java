package core;

public class Cycle{
    private boolean isRunning;
    private JUIXApplication application;
    public Cycle(JUIXApplication application) {
        this.application= application;
        isRunning=true;
    }

    public void start(){
        //initialization
        long startTime;
        int FPS=0;
        int ticks=0;
        double nanoPerTick = 1000000000/(float)application.getTicksPerSecond();
        System.out.println(nanoPerTick);
        double unprocessedTicks=0;
        double lastNano=System.nanoTime();
        long lastMilis=System.currentTimeMillis();
        int tickCounter=0;
        //Main loop
        while(isRunning){
            double nowNano=System.nanoTime();
            unprocessedTicks+=(nowNano-lastNano)/nanoPerTick;
            lastNano=nowNano;
            //Ticks processing
            while(unprocessedTicks >1){
                application.update(tickCounter);
                tickCounter++;
                ticks++;
                unprocessedTicks--;
            }

            //Drawing
            application.draw();

        }

    }

    public void stop(){
        isRunning=false;
    }
}
