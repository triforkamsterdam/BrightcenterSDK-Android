package nl.trifork.brightcenter.androidsdk;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author Rick Slot
 */
public class OverlayButton{

    public Button setParams(Button button, int position, int color, Resources res){
        switch (position) {
            case 1:

                switch(color){
                    case 1:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_top_left));
                        break;
                    case 2:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_blue_top_left));
                        break;
                    case 3:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_gray_top_left));
                        break;
                    default:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_top_left));
                        break;

                }
                break;
            case 2:

                switch(color){
                    case 1:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_top_right));
                        break;
                    case 2:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_blue_top_right));
                        break;
                    case 3:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_gray_top_right));
                        break;
                    default:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_top_right));
                        break;
                }
                break;
            case 3:

                switch(color){
                    case 1:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_bottom_left));
                        break;
                    case 2:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_blue_bottom_left));
                        break;
                    case 3:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_gray_bottom_left));
                        break;
                    default:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_bottom_left));
                        break;
                }
                break;
            case 4:
                switch(color){
                    case 1:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_bottom_right));
                        break;
                    case 2:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_blue_bottom_right));
                        break;
                    case 3:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_gray_bottom_right));
                        break;
                    default:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_bottom_right));
                        break;
                }
                break;
            default:
                switch(color){
                    case 1:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_bottom_right));
                        break;
                    case 2:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_blue_bottom_right));
                        break;
                    case 3:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_gray_bottom_right));
                        break;
                    default:
                        button.setBackground(res.getDrawable(R.drawable.overlay_button_background_orange_bottom_right));
                        break;
                }
                break;
        }

        return button;
    }
}