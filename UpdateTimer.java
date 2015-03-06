
public class UpdateTimer {
	private boolean running;
	private long lastUpdate,currentTime;
	private BoxDropper o;
	public UpdateTimer(BoxDropper bd){
		o=bd;
	}
	public void stop(){
		running = false;
	}
	public void start(){
		running = true;
		lastUpdate = System.nanoTime()/1000000;
		while(running){
			currentTime = System.nanoTime()/1000000;
			if(currentTime>lastUpdate+16){
				lastUpdate = currentTime;
			}
		}
	}
}
