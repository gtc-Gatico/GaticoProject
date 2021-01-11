package cn.com.gatico.jme;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;

import java.io.IOException;

public class RotateControl implements Control {
    private Spatial spatial;

    // 旋转速度：每秒180°
    private float rotateSpeed = FastMath.PI;

    public RotateControl() {
        this.rotateSpeed = FastMath.PI;
    }

    public RotateControl(float rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    @Override
    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    @Override
    public void update(float tpf) {
        spatial.rotate(0, tpf * rotateSpeed, 0);
    }

    @Override
    public void render(RenderManager rm, ViewPort vp) {
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new IOException("暂不支持");
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        throw new IOException("暂不支持");
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        RotateControl c = new RotateControl(rotateSpeed);
        c.setSpatial(spatial);
        return c;
    }
}
