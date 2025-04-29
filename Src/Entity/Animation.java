import java.awt.image. BufferedImage;



public class Animation {
    private BufferedImage [] frames;
    private int currentFrame;

    private long startTime;
    private long delay;
     
    private boolean playdOne;

    public void Animation () {
        boolean playedOnce = false;
    }
    public void setFrames ( BufferedImage [] frames){
        this.frames = frames;
        currentFrame = 0 ;
        startTime = System. nanoTime();
        playdOne = false;

    }
    public void setDelay (long d) { delay =d ; }
    public void setFrame (int i) {currentFrame = i ; }

    public void update (){

        long elapsed =( System.nanoTime()-startTime)/1000000;
        if ( elapsed > delay){
            currentFrame++ ;
            startTime = System.nanoTime() ;
    
        }
        if (currentFrame == frames.length) {
            currentFrame = 0 ;
            playdOne = true;
        }
    }
    public int getFrames () {return currentFrame;}
    public BufferedImage getImage() {return frames[currentFrame];}
    public boolean hasPlayedOnce() {return playdOne;}
}
