package com.video45.video.editor;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.video45.video.editor.tools.Decoder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by nicholas on 28/11/15.
 */
public class VideoEditorView extends GLSurfaceView {

    private final Uri uri;

    private final VideoEditorRenderer renderer;
    private VideoFrame videoFrame;
    private Decoder decoder;

    public VideoEditorView(Context context, Uri uri) {
        super(context);

        this.uri = uri;

        setEGLContextClientVersion(2);

        renderer = new VideoEditorRenderer();

        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public class VideoEditorRenderer implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            videoFrame = new VideoFrame();
            try {
                decoder = new Decoder(uri, getHolder().getSurface());
            } catch (IOException | Decoder.CodecNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

        }

        @Override
        public void onDrawFrame(GL10 gl) {
//            videoFrame.draw();
        }
    }

    private class VideoFrame {

        public VideoFrame(){
            initializeBuffers();
            initializeProgram();
        }

        private float[] vertices = {
                -1f, -1f,
                1f, 1f,
                -1f, 1f,
                1f, -1f
        };

        private float[] textureVertices = {
                0f, 1f,
                1f, 1f,
                0f,0f,
                1f, 0f
        };

        private final String vertexShaderCode =
                "attribute vec4 aPosition;" +
                        "attribute vec2 aTexPosition;" +
                        "varying vec2 vTexPosition;" +
                        "void main() {" +
                        "  gl_Position = aPosition;" +
                        "  vTexPosition = aTexPosition;" +
                        "}";

        private final String fragmentShaderCode =
                "precision mediump float;" +
                        "uniform sampler2D uTexture;" +
                        "varying vec2 vTexPosition;" +
                        "void main() {" +
                        "  gl_FragColor = texture2D(uTexture, vTexPosition);" +
                        "}";


        private int vertexShader;
        private int fragmentShader;
        private int program;

        private FloatBuffer verticesBuffer;
        private FloatBuffer textureBuffer;

        private void initializeBuffers() {
            ByteBuffer buff = ByteBuffer.allocateDirect(vertices.length * 4);
            buff.order(ByteOrder.nativeOrder());
            verticesBuffer = buff.asFloatBuffer();
            verticesBuffer.put(vertices);
            verticesBuffer.position(0);

            buff = ByteBuffer.allocateDirect(textureVertices.length * 4);
            buff.order(ByteOrder.nativeOrder());
            textureBuffer = buff.asFloatBuffer();
            textureBuffer.put(textureVertices);
            textureBuffer.position(0);
        }



        private void initializeProgram() {
            vertexShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(vertexShader, vertexShaderCode);
            GLES20.glCompileShader(vertexShader);

            fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
            GLES20.glCompileShader(fragmentShader);

            program = GLES20.glCreateProgram();
            GLES20.glAttachShader(program, vertexShader);
            GLES20.glAttachShader(program, fragmentShader);

            GLES20.glLinkProgram(program);
        }

        public void draw(int texture){
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
            GLES20.glUseProgram(program);
            GLES20.glDisable(GLES20.GL_BLEND);

            int positionHandle = GLES20.glGetAttribLocation(program, "aPosition");
            int textureHandle = GLES20.glGetUniformLocation(program, "uTexture");
            int texturePositionHandle = GLES20.glGetAttribLocation(program, "aTexPosition");

            GLES20.glVertexAttribPointer(texturePositionHandle, 2, GLES20.GL_FLOAT, false, 0, textureBuffer);
            GLES20.glEnableVertexAttribArray(texturePositionHandle);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
            GLES20.glUniform1i(textureHandle, 0);

            GLES20.glVertexAttribPointer(positionHandle, 2, GLES20.GL_FLOAT, false, 0, verticesBuffer);
            GLES20.glEnableVertexAttribArray(positionHandle);

            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        }
    }

}
