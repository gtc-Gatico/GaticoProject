package cn.com.gatico.jme;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.LightList;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.shadow.SpotLightShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;

public class TestLight extends SimpleApplication {
    Geometry domeGeometry;
    boolean up = false, down = false, left = false, right = false;
    float speed = 0;
    Node rootNode = new Node();
    private Vector3f[] location = {
            new Vector3f(36, 36, 36),
            new Vector3f(36, 36, -36),
            new Vector3f(-36, 36, 36),
            new Vector3f(-36, 36, -36),
    };

    public static void main(String[] args) {
        TestLight testLight = new TestLight();
        AppSettings appSettings = new AppSettings(false);
//        testLight.setShowSettings(false);
        appSettings.setFullscreen(false);
        appSettings.setWidth(400);
        appSettings.setHeight(400);
        testLight.setSettings(appSettings);
        testLight.start();
    }

    @Override
    public void simpleInitApp() {
        getCamera().setLocation(new Vector3f(36, 36, 36));
        getCamera().lookAt(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));

        createFloor();
        createBox();

        initLight();
        
        //录屏
//        try {
//            if (false) {
//                File video = new File("D:\\JME-water-video.avi");
//                stateManager.attach(new VideoRecorderAppState(video)); //start recording
//                System.out.println(video.getCanonicalPath());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("ok");
    }


    public void simpleUpdate(float tpf) {
        int index = (int) (System.currentTimeMillis() / 1000 % location.length);
        domeGeometry.setLocalTranslation(location[index]);

        LightList lights = rootNode.getLocalLightList();
        for (int i = 0; i < lights.size(); i++) {
            if ("spot".equals(lights.get(i).getName())) {
                lights.get(i).setColor(ColorRGBA.randomColor());
            }
        }
        Spatial box = rootNode.getChild("box");
        Vector3f localTranslation = box.getLocalTranslation();
        if (up) {
            localTranslation.setZ(localTranslation.getZ() + speed);
            box.setLocalTranslation(localTranslation);
        }
        if (down) {
            localTranslation.setZ(localTranslation.getZ() - speed);
            box.setLocalTranslation(localTranslation);
        }
        if (left) {
            localTranslation.setX(localTranslation.getX() - speed);
            box.setLocalTranslation(localTranslation);
        }
        if (right) {
            localTranslation.setX(localTranslation.getX() + speed);
            box.setLocalTranslation(localTranslation);
        }
//        Vector3f localTranslation1 = box.getLocalTranslation();
//        localTranslation1.setY(localTranslation1.getY() + 20);
//        getCamera().setLocation(localTranslation1);
    }

    public void createFloor() {
        Geometry floorGeometry = new Geometry();
        Box floor = new Box(36, 0.1f, 36);
        floor.scaleTextureCoordinates(new Vector2f(6, 6));
        Texture floorTexture = getAssetManager().loadTexture("floor.jpg");
        floorTexture.setWrap(Texture.WrapMode.Repeat);
        Material floorMaterial = new Material( getAssetManager(), MaterialUtils.Lighting);
        floorMaterial.setTexture("DiffuseMap", floorTexture);
        floorGeometry.setMesh(floor);
        floorGeometry.setMaterial(floorMaterial);
        floorGeometry.setShadowMode(RenderQueue.ShadowMode.Receive);
        floorGeometry.center();
        rootNode.attachChild(floorGeometry);
    }

    public void createBox() {
        Geometry boxGeometry = new Geometry("box");
        Box box = new Box(6, 6, 6);
        Texture boxTexture =  getAssetManager().loadTexture("stone.jpg");
        Material boxMaterial = new Material( getAssetManager(), MaterialUtils.Lighting);
        boxMaterial.setTexture("DiffuseMap", boxTexture);
        boxGeometry.setMesh(box);
        boxGeometry.setMaterial(boxMaterial);
        boxGeometry.setShadowMode(RenderQueue.ShadowMode.Cast);
        boxGeometry.setLocalTranslation(0, 6.1f, 0);
        rootNode.attachChild(boxGeometry);
    }

    public void initLight() {
        AmbientLight ambientLight = new AmbientLight(ColorRGBA.White);
        rootNode.addLight(ambientLight);

        domeGeometry = new Geometry();
        Dome dome = new Dome(10, 25, 25 * FastMath.DEG_TO_RAD);
        Material domeMaterial = new Material( getAssetManager(), MaterialUtils.Lighting);
        domeGeometry.setMesh(dome);
        domeGeometry.setMaterial(domeMaterial);
        domeGeometry.setLocalTranslation(location[0]);

        SpotLight spotLight = new SpotLight();
        spotLight.setName("spot");
        spotLight.setSpotRange(200);
        spotLight.setSpotInnerAngle(30 * FastMath.DEG_TO_RAD);
        spotLight.setSpotOuterAngle(70 * FastMath.DEG_TO_RAD);
        spotLight.setColor(ColorRGBA.randomColor().mult(3));
        spotLight.setPosition(domeGeometry.getLocalTranslation());
        spotLight.setDirection(new Vector3f(0, 0, 0));
        SpotLightShadowRenderer spotLightShadowRenderer = new SpotLightShadowRenderer( getAssetManager(), 1024);
        spotLightShadowRenderer.setLight(spotLight);
        spotLightShadowRenderer.setEdgeFilteringMode(EdgeFilteringMode.PCFPOISSON);
        getViewPort().addProcessor(spotLightShadowRenderer);
        LightControl lightControl = new LightControl(spotLight);
        domeGeometry.addControl(lightControl);
        rootNode.addLight(spotLight);
        rootNode.attachChild(domeGeometry);
    }

    private void initControl(String name, int keyCode, ActionListener listener) {
        getInputManager().addMapping(name, new KeyTrigger(keyCode));

        // 绑定消息和监听器
        getInputManager().addListener(listener, name);
    }
}
