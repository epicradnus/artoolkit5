package org.artoolkit.ar.samples.ARSimple.shader;

import android.opengl.GLES20;

import org.artoolkit.ar.base.rendering.gles20.OpenGLShader;
import org.artoolkit.ar.base.rendering.gles20.ShaderProgram;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by Thorsten Bux on 21.01.2016.
 */
public class SimpleShaderProgram extends ShaderProgram {

    public SimpleShaderProgram(OpenGLShader vertexShader, OpenGLShader fragmentShader){
        super(vertexShader,fragmentShader);
        bindAttributes();
    }

    @Override
    public int getProjectionMatrixHandle() {
        return GLES20.glGetUniformLocation(shaderProgramHandle, OpenGLShader.projectionMatrixString);
    }

    @Override
    public int getModelViewMatrixHandle() {
        return GLES20.glGetUniformLocation(shaderProgramHandle,OpenGLShader.modelViewMatrixString);
    }

    @Override
    protected void bindAttributes(){
        // Bind attributes
        GLES20.glBindAttribLocation(shaderProgramHandle, 0, OpenGLShader.positionVectorString);
        GLES20.glBindAttribLocation(shaderProgramHandle, 1, SimpleVertexShader.colorVectorString);
    }

    public int getPositionHandle() {
        return GLES20.glGetAttribLocation(shaderProgramHandle, OpenGLShader.positionVectorString);
    }

    public int getColorHandle() {
        return GLES20.glGetAttribLocation(shaderProgramHandle, SimpleVertexShader.colorVectorString);
    }

    @Override
    public void render(FloatBuffer vertexBuffer, FloatBuffer colorBuffer, ByteBuffer indexBuffer) {
        setupShaderUsage();

        // Pass in the position information
        vertexBuffer.position(0);
        GLES20.glVertexAttribPointer(this.getPositionHandle(), positionDataSize, GLES20.GL_FLOAT, false,
                positionStrideBytes, vertexBuffer);

        GLES20.glEnableVertexAttribArray(this.getPositionHandle());

        // Pass in the color information
        colorBuffer.position(0);
        GLES20.glVertexAttribPointer(this.getColorHandle(), colorDataSize, GLES20.GL_FLOAT, false,
                colorStrideBytes, colorBuffer);

        GLES20.glEnableVertexAttribArray(this.getColorHandle());

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 36, GLES20.GL_UNSIGNED_BYTE, indexBuffer);

    }
}
