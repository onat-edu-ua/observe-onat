package ua.edu.onat.observonat.Helpers;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(new Renderer() {
            private FloatBuffer textureCoordinates;
            private FloatBuffer normals;
            private FloatBuffer positions;

            @Override
            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            }

            @Override
            public void onSurfaceChanged(GL10 gl10, int width, int height) {
                GLES20.glViewport(0, 0, width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl10) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            }
        });
    }
}
