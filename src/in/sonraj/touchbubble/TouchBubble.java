package in.sonraj.touchbubble;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

public class TouchBubble extends AndroidNonvisibleComponent {

  private Bubble bubble;
  private Bubble.Builder builder;
  private final Activity activity;
  private int color=Color.CYAN;
  private float alpha=1f;
  private int duration=2000;
  private int radius=40;

  public TouchBubble(ComponentContainer container) {
    super(container.$form());
    this.activity= container.$context();
  }

  @SimpleProperty
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_CYAN)
  public void BubbleColor(int bubbleColor){
    this.color=bubbleColor;
  }

  @SimpleProperty
  public int BubbleColor(){
    return builder.getBubbleColor();
  }

  @SimpleProperty(description = "Set the alpha of bubble, [0, 1].")
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT, defaultValue = "1")
  public void Alpha(float alpha){
    this.alpha=alpha;
  }

  @SimpleProperty
  public float Alpha(){
    return builder.getAlpha();
  }

  @SimpleProperty(description = "Set the duration of animation in millisecond.")
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER, defaultValue = "2000")
  public void Duration(int duration){
    this.duration=duration;
  }

  @SimpleProperty
  public int Duration(){
    return builder.getDuration();
  }

  @SimpleProperty
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER, defaultValue = "40")
  public void BubbleRadius(int radius){
    this.radius=radius;
  }

  @SimpleProperty
  public int BubbleRadius(){
    return builder.getDuration();
  }

  @SuppressLint("ClickableViewAccessibility")
  @SimpleFunction(description = "Setup the view.")
  public void BubbleView(AndroidViewComponent view, boolean customPosition) {
    ViewGroup viewGroup= (ViewGroup) view.getView();
    builder=new Bubble.Builder(viewGroup);
    bubble = new Bubble(builder);
    builder.setAlpha(alpha);
    builder.setBubbleColor(color);
    builder.setDuration(duration);
    builder.setInterpolator(new LinearInterpolator());
    builder.setBubbleRadius(radius);
    builder.build();
    view.getView().setOnTouchListener((view1, motionEvent) -> {

      activity.runOnUiThread(() -> OnTouch(motionEvent.getX(), motionEvent.getY()));
      if(!customPosition){
        bubble.shoot((int) motionEvent.getX(), (int) motionEvent.getY());
      }

      return false;
    });
  }

  @SimpleFunction
  public void CustomPosition(int x, int y) {
    bubble.shoot(x, y);
  }


  @SimpleFunction(description = " Stop the bubble animations.")
  public void StopAnimation(){
    bubble.stop();
  }

  @SimpleFunction(description = "Stop the bubble animations & Remove the bubble view so that you can not show bubbles anymore.")
  public void RemoveView(){
    bubble.destroy();
  }

  @SimpleEvent
  public void OnTouch(float x, float y){
    EventDispatcher.dispatchEvent(this,"OnTouch", x, y);
  }

  /*
  @SuppressLint("ClickableViewAccessibility")
  @SimpleFunction
  public void SetTouchListenerView(AndroidViewComponent view){
   view.getView().setOnTouchListener((view1, motionEvent) -> {
     OnTouch(motionEvent.getX(), motionEvent.getY());
     return false;
   });
  }

  @SimpleFunction(description = "Returns the sum of the given list of integers.")
  public void BubbleFullScreen() {
    bubble = new Bubble(new Bubble.Builder(activity));
    Bubble.Builder builder=new Bubble.Builder(activity);
    builder.setAlpha(0.5f);
    builder.setBubbleColor(Color.YELLOW);
    builder.setDuration(4000);
    builder.setInterpolator(new LinearInterpolator());
    builder.setBubbleRadius(30);
    builder.build();
  }
  */

}
