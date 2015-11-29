package com.video45.video.editor;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by nicholas on 28/11/15.
 */
public class VideoEditorView extends GLSurfaceView {

    private final VideoEditorRenderer renderer;

    public VideoEditorView(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        renderer = new VideoEditorRenderer();

        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

}
