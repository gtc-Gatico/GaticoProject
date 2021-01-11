package cn.com.gatico.jme;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.app.state.VideoRecorderAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.shadow.SpotLightShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Test extends SimpleApplication {
    /**
     * 开火消息
     */
    public final static String FIRE = "Fire";

    List<SpotLight> spotLights = new LinkedList<>();

    boolean initFlag = false;

    int shadowMapSize = 1024;

    EdgeFilteringMode mode = EdgeFilteringMode.PCFPOISSON;

    SpotLightShadowRenderer spotLightShadowRenderer;

    Geometry spotGeom;

    Geometry geom;

    /**
     * 初始化3D场景，显示一个方块。
     */

    @Override
    public void simpleInitApp() {

        // 绑定消息和触发器
        inputManager.addMapping(FIRE, new KeyTrigger(KeyInput.KEY_SPACE), new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

        // 绑定消息和监听器
        inputManager.addListener(new MyActionListener(), FIRE);

        getCamera().setName("主的");
        getCamera().setLocation(new Vector3f(25f, 50f, 25f));
        getCamera().lookAt(new Vector3f(0, 0, 0), Vector3f.UNIT_Y);


        // 不感光材质 "Common/MatDefs/Misc/Unshaded.j3md"  感光材质Common/MatDefs/Light/Lighting.j3md
        /**
         * 在Common/MatDefs/Misc/Unshaded.j3md中，通常使用下面的参数：
         *
         * Color : ColorRGBA，表示模型颜色
         * ColorMap : Texture，表示模型表面的纹理贴图
         * LightMap : Texture，表示烘焙的亮度贴图
         * UseSperatedTex
         * 在Common/MatDefs/Light/Lighting.j3md中，通常使用下面这些参数：
         *
         * Ambient : ColorRGBA，环境光色
         * Diffuse : ColorRGBA，漫反射颜色
         * Specular : ColorRGBA，高光颜色
         * UseMaterialColors : Boolean，是否使用材质中定义的颜色。
         * Shininess : Float，反光度。1.0最低，128最高。
         * DiffuseMap : Texture2D，漫反射贴图
         * NormalMap : Texture2D，法线贴图
         * SpecularMap : Texture2D，高光贴图
         */
        Box floor = new Box(25, 1f, 25);
        floor.scaleTextureCoordinates(new Vector2f(5, 5));
        Material floorMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        Geometry floorGeom = new Geometry("floor");
        Texture tex = assetManager.loadTexture("floor.jpg");
        tex.setWrap(Texture.WrapMode.Repeat);
//        floorMat.setTexture("DiffuseMap", tex);
//        tex = assetManager.loadTexture("stone-bump.jpg");
//        floorMat.setTexture("NormalMap", tex);
        floorGeom.setLocalTranslation(new Vector3f(0, -25, 0));
        floorMat.setTexture("DiffuseMap",tex);
        floorGeom.setMesh(floor);
        floorGeom.setMaterial(floorMat);
        floorGeom.setShadowMode(RenderQueue.ShadowMode.Receive);// 承载阴影
        floorGeom.center();
        rootNode.attachChild(floorGeom);

        // #1 创建一个方块形状的网格
        Box box = new Box(2f, 2f, 2f);
        // #2 加载一个感光材质
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        // #3 创建一个几何体，应用刚才和网格和材质。
        geom = new Geometry("Box");
        tex = assetManager.loadTexture("metal.jpg");
        mat.setTexture("DiffuseMap", tex);
//        tex = assetManager.loadTexture("metal-normal.jpg");
//        mat.setTexture("NormalMap", tex);
        mat.setFloat("Shininess",128);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setBoolean("UseMaterialColors", false);
        geom.setMesh(box);
        geom.setMaterial(mat);
        geom.setShadowMode(RenderQueue.ShadowMode.Cast);
        geom.center();
        geom.setLocalTranslation(new Vector3f(0, 2, 0));
        rootNode.attachChild(geom);


        // #4 创建一束阳光，并让它斜向下照射，好使我们能够看清那个方块。
//        DirectionalLight left = new DirectionalLight();
//        left.setColor(ColorRGBA.Yellow);
//        left.setDirection(new Vector3f(0,0,-1));
//        rootNode.addLight(left);
//
//        DirectionalLight right = new DirectionalLight();
//        right.setColor(ColorRGBA.Green);
//        right.setDirection(new Vector3f(-1,0,0));
//        rootNode.addLight(right);

        // #4 创建一束阳光，并让它斜向下照射，好使我们能够看清那个方块。
//        DirectionalLight top = new DirectionalLight();
//        top.setColor(ColorRGBA.White);
//        top.setDirection(new Vector3f(-1,-1,-1));
//        rootNode.addLight(top);
//        final int SHADOWMAP_SIZE=1024;
//        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
//        dlsr.setLight(sun);
//        viewPort.addProcessor(dlsr);


        System.out.println(cam.getDirection());
        System.out.println(cam.getLocation());

        //后左
        SpotLight spot0 = new SpotLight();
        spot0.setSpotRange(100f);                           // 距离
        spot0.setSpotInnerAngle(25f * FastMath.DEG_TO_RAD); // 内光锥(中心光束)
        spot0.setSpotOuterAngle(35f * FastMath.DEG_TO_RAD); // 外光锥(光的边缘)
        spot0.setColor(ColorRGBA.Red);         // 颜色，光强
        spot0.setPosition(new Vector3f(box.getCenter().x - 25, box.getCenter().y + 25, box.getCenter().z - 25));               // 从相机方向发光
        spot0.setDirection(new Vector3f(1, -1, 1));             // 向镜头前面发光
        spot0.setName(String.valueOf(ColorRGBA.Red.asIntARGB()));
        rootNode.addLight(spot0);
        spotLights.add(spot0);

        //后右
        SpotLight spot1 = new SpotLight();
        spot1.setSpotRange(100f);                           // 距离
        spot1.setSpotInnerAngle(25f * FastMath.DEG_TO_RAD); // 内光锥(中心光束)
        spot1.setSpotOuterAngle(35f * FastMath.DEG_TO_RAD); // 外光锥(光的边缘)
        spot1.setColor(ColorRGBA.Yellow);         // 颜色，光强
        spot1.setPosition(new Vector3f(box.getCenter().x + 25, box.getCenter().y + 25, box.getCenter().z - 25));               // 从相机方向发光
        spot1.setDirection(new Vector3f(-1, -1, 1));             // 向镜头前面发光
        spot1.setName(String.valueOf(ColorRGBA.Yellow.asIntARGB()));
        rootNode.addLight(spot1);
        spotLights.add(spot1);

        //前右
        SpotLight spot3 = new SpotLight();
        spot3.setSpotRange(100f);                           // 距离
        spot3.setSpotInnerAngle(25f * FastMath.DEG_TO_RAD); // 内光锥(中心光束)
        spot3.setSpotOuterAngle(35f * FastMath.DEG_TO_RAD); // 外光锥(光的边缘)
        spot3.setColor(ColorRGBA.Green);         // 颜色，光强
        spot3.setPosition(new Vector3f(box.getCenter().x + 25, box.getCenter().y + 25, box.getCenter().z + 25));               // 从相机方向发光
        spot3.setDirection(new Vector3f(-1, -1, -1));             // 向镜头前面发光
        spot3.setName(String.valueOf(ColorRGBA.Green.asIntARGB()));
        rootNode.addLight(spot3);
        spotLights.add(spot3);

        //前左
        SpotLight spot2 = new SpotLight();
        spot2.setSpotRange(100f);                           // 距离
        spot2.setSpotInnerAngle(25f * FastMath.DEG_TO_RAD); // 内光锥(中心光束)
        spot2.setSpotOuterAngle(35f * FastMath.DEG_TO_RAD); // 外光锥(光的边缘)
        spot2.setColor(ColorRGBA.Blue);         // 颜色，光强
        spot2.setPosition(new Vector3f(box.getCenter().x - 25, box.getCenter().y + 25, box.getCenter().z + 25));               // 从相机方向发光
        spot2.setDirection(new Vector3f(1, -1, -1));             // 向镜头前面发光
        spot2.setName(String.valueOf(ColorRGBA.Blue.asIntARGB()));
        rootNode.addLight(spot2);
        spotLights.add(spot2);

        Box spotBox = new Box(1f, 1f, 1f);
        // #2 加载一个感光材质
        Material spotMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        // #3 创建一个几何体，应用刚才和网格和材质。
        spotGeom = new Geometry("Box");
        spotGeom.setLocalTranslation(spot1.getPosition());
        spotGeom.setMesh(spotBox);
        spotGeom.setMaterial(spotMat);
        spotGeom.addControl(new RotateControl(FastMath.PI));
        rootNode.attachChild(spotGeom);

        SpotLight spotLight = new SpotLight();
        spotLight.setName("Test");
        spotLight.setColor(ColorRGBA.White.mult(2));
        spotLight.setPosition(box.center);
        spotLight.getPosition().setY(20);
        spotLight.setSpotInnerAngle(20 * FastMath.DEG_TO_RAD);
        spotLight.setSpotOuterAngle(80 * FastMath.DEG_TO_RAD);
        spotLight.setDirection(new Vector3f(0,-1,0));
        rootNode.addLight(spotLight);

        LightControl lightControl = new LightControl(spotLight);
        spotGeom.addControl(lightControl);

        Line line = new Line(box.center,cam.getLocation());
        Material lineMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        lineMaterial.setColor("Color",ColorRGBA.Red.mult(2));
        Geometry lineGeom = new Geometry("line");
        lineGeom.setMesh(line);
        lineGeom.setMaterial(lineMaterial);
        rootNode.attachChild(lineGeom);


        // 环境光
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White.mult(.4f));
        rootNode.addLight(ambient);
        // #5 将方块和都添加到场景图中
        inputManager.setCursorVisible(true);
        viewPort.setBackgroundColor(ColorRGBA.White);
        //截屏
        ScreenshotAppState screenShotState = new ScreenshotAppState();
        this.stateManager.attach(screenShotState);

        //录屏
        try {
            if (false) {
                File video = new File("D:\\JME-water-video.avi");
                stateManager.attach(new VideoRecorderAppState(video)); //start recording
                System.out.println(video.getCanonicalPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //loadModel();
        initFlag = true;
    }

    /**
     * 主循环
     */
    @Override
    public void simpleUpdate(float deltaTime) {
        if (initFlag) {
            int current = (int) (System.currentTimeMillis() / 1000 % spotLights.size());
//            System.out.println(current);
            for (int i = 0; i < spotLights.size(); i++) {
                SpotLight spotLight = spotLights.get(i);
                if (spotLight != null && spotLight.getName() != null) {
                    spotLights.get(i).setColor(ColorRGBA.Black);
                    if (spotLightShadowRenderer != null) {
                        viewPort.removeProcessor(spotLightShadowRenderer);
                    }
                }
            }
            SpotLight spotLight = spotLights.get(current);
            ColorRGBA c = new ColorRGBA();
            c.fromIntARGB(Integer.parseInt(spotLight.getName()));
            spotLight.setColor(c);
            spotLightShadowRenderer = new SpotLightShadowRenderer(assetManager, shadowMapSize);
            spotLightShadowRenderer.setLight(spotLight);
            spotLightShadowRenderer.setEdgeFilteringMode(mode);
            viewPort.addProcessor(spotLightShadowRenderer);
            spotGeom.setLocalTranslation(spotLight.getPosition());
            geom.getMaterial().setColor("Diffuse",spotLight.getColor());

        }
        // 旋转速度：每秒360°
        //    float speed = FastMath.TWO_PI;
        // 让方块匀速旋转
//        geom.rotate(0, deltaTime * speed, 0);
//        System.out.format("相机[%s]:[宽：%d],[高：%d],[近：%f],[远：%f],[上：%f],[下：%f]",
//                getCamera().getName(),
//                getCamera().getWidth(),
//                getCamera().getHeight(),
//                getCamera().getFrustumNear(),
//                getCamera().getFrustumFar(),
//                getCamera().getFrustumTop(),
//                getCamera().getFrustumBottom()
//        );
//        System.out.println("---------------");


    }

    /**
     * 加载模型
     */
    private void loadModel() {
        new Thread() {
            public void run() {
                // 导入模型
                final Spatial model = assetManager.loadModel("Models/Ashe/b_ashe_b.obj");
                model.scale(0.03f);// 按比例缩小
                model.center();// 将模型的中心移到原点

                // 通知主线程，将模型添加到场景图中。
                enqueue(new Runnable() {
                    public void run() {
                        rootNode.attachChild(model);
                    }
                });
            }
        }.start();
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 配置参数
        AppSettings settings = new AppSettings(true);
        //设置全屏
//        settings.setFullscreen(true);
        //设置标题
        settings.setTitle("一个方块");
        //设置显示窗口大小
        settings.setResolution(400, 400);
        //设置帧数
        settings.setFrameRate(60);
        //设置色深 1：黑白、2：灰度色、4：16色、8：256色、24/32：真菜
        settings.setBitsPerPixel(32);
        //设置抗锯齿 0：关闭抗锯齿、2:2倍抗锯齿、4：4倍抗锯齿、8：8倍抗锯齿、32：32倍抗锯齿
        settings.setSamples(32);
        //垂直同步
        settings.setVSync(false);
        //限制输入
        settings.setUseInput(true);
        //激活操纵杆
        settings.setUseJoysticks(false);
        // 启动jME3程序
        Test app = new Test();
        app.setSettings(settings);// 应用参数
        app.setShowSettings(false);
        app.settings.setSettingsDialogImage("F:\\库\\图片\\5.jpg");
        app.start();
    }

    class MyActionListener implements ActionListener {
        public void onAction(String name, boolean isPressed, float tpf) {
            if (FIRE.equals(name) && isPressed) {
                System.out.println("bang!");
                getCamera().setLocation(spotGeom.getLocalTranslation());
                getCamera().lookAt(new Vector3f(0, 0, 0), new Vector3f());
            }
        }

    }
}


