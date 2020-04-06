package com.game.Behaviors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

//Class to implement animations.
public class Animation {
    private Array<TextureRegion> frames;
    private float totalFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int currentFrame;

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = (region.getRegionWidth() / frameCount);
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        totalFrameTime = cycleTime / frameCount;
        currentFrame = 0;
    }

    //update counters for animations
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > totalFrameTime){
            currentFrame++;
            currentFrameTime = 0;
        }
        if(currentFrame >= frameCount){
            currentFrame = 0;
        }
    }

    //returns region of the sheet where the current frame is.
    public TextureRegion getFrame(){
        return frames.get(currentFrame);
    }
}
