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
                //                     r       g         b        a
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                ObjLoader objLoader = new ObjLoader(context, "Mug.obj");
                int numFaces = objLoader.numFaces;

// Initialize the buffers.
                positions = ByteBuffer.allocateDirect(objLoader.positions.length * mBytesPerFloat)
                        .order(ByteOrder.nativeOrder()).asFloatBuffer();
                positions.put(objLoader.positions).position(0);

                normals = ByteBuffer.allocateDirect(objLoader.normals.length * mBytesPerFloat)
                        .order(ByteOrder.nativeOrder()).asFloatBuffer();
                normals.put(objLoader.normals).position(0);

                textureCoordinates = ByteBuffer.allocateDirect(objLoader.textureCoordinates.length * mBytesPerFloat)
                        .order(ByteOrder.nativeOrder()).asFloatBuffer();
                textureCoordinates.put(objLoader.textureCoordinates).position(0);
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
