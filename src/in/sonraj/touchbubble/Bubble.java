package in.sonraj.touchbubble;

import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;


public class Bubble {
    private final BubbleView mBubbleView;

    Bubble(Builder builder){
        mBubbleView = new BubbleView(builder);
        builder.rootView.addView(mBubbleView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * Shoot >>>>>>>>>>>>>>>>>>>>.
     * @param x The X coordinate relative to the root view.
     * @param y The y coordinate relative to the root view.
     * */
    public void shoot(int x, int y){
        mBubbleView.shoot(x, y);
    }

    /**
     * Stop the bubble animations.
     * */
    public void stop(){
        mBubbleView.stop();
    }

    /**
     * Stop the bubble animations & Remove the bubble view so that you can not show bubbles anymore.
     * */
    public void destroy(){
        mBubbleView.destroy();
    }

    /**
     * To config the params of {@link Bubble} uniformly, the you can build it.
     * */
    public static class Builder{
        private final ViewGroup rootView;
        private float alpha = 1f;
        private int duration = 2000;
        private TimeInterpolator timeInterpolator = new LinearInterpolator();
        private float bubbleRadius = 40;
        private int bubbleColor = Color.TRANSPARENT;

        /**
         * Will use the activity's decorView as the root view to add {@link BubbleView}.
         *
        public Builder(Activity activity){
            this((ViewGroup) activity.getWindow().getDecorView());
        }


         * Will use the viewGroup as the root view to add {@link BubbleView}.
         * Note: It's recommended to use {@link android.widget.RelativeLayout},
         * {@link android.widget.FrameLayout}.It is not recommended to use {@link android.widget.LinearLayout}.
         * */
        public Builder(ViewGroup viewGroup){
            if (viewGroup == null){
                throw new NullPointerException("activity can not be null.");
            }

            rootView = viewGroup;
        }

        public ViewGroup getRootView() {
            return rootView;
        }

        /**
         * Set the alpha of bubble, [0, 1].
         * If not specified, the default is 1.
         * */
        public void setAlpha(float alpha){
            this.alpha = alpha;
        }


        public float getAlpha() {
            return alpha;
        }

        /**
         * Set the duration of animation in millisecond.
         * If not specified, the default is 500ms.
         * */
        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getDuration() {
            return duration;
        }

        /**
         * Set the interpolator of animation.
         * If not specified, the default is {@link LinearInterpolator}.
         * */
        public void setInterpolator(TimeInterpolator timeInterpolator) {
            this.timeInterpolator = timeInterpolator;
        }

        public TimeInterpolator getInterpolator() {
            return timeInterpolator;
        }

        /**
         * Set the radius of bubble.
         * If not specified, the default is 100.
         * */
        public void setBubbleRadius(float bubbleRadius) {
            this.bubbleRadius = bubbleRadius;
        }

        public float getBubbleRadius() {
            return bubbleRadius;
        }

        /**
         * Set the color of bubble.
         * If not specified, the default is {@link Color#WHITE}.
         * */
        public void setBubbleColor(int bubbleColor) {
            this.bubbleColor = bubbleColor;
        }

        public int getBubbleColor() {
            return bubbleColor;
        }

        /**
         * To build a bubble obj to use the function of {@link Bubble}.
         * */
        public void build(){
            new Bubble(this);
        }
    }
}
