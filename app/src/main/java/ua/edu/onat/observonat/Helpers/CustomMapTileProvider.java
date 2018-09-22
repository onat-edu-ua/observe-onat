package ua.edu.onat.observonat.Helpers;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomMapTileProvider implements TileProvider {
    private static final int TILE_WIDTH = 256;
    private static final int TILE_HEIGHT = 256;
    private static final int BUFFER_SIZE = 16 * 1024;

    private AssetManager mAssets;

    public CustomMapTileProvider(AssetManager assets) {
        mAssets = assets;
    }

    @Override
    public Tile getTile(int x, int y, int zoom) {
//        if(zoom<18)
//            return null;
        byte[] image = readTileImage(x, y, zoom);
        return image == null ? null : new Tile(TILE_WIDTH, TILE_HEIGHT, image);
    }

    private byte[] readTileImage(int x, int y, int zoom) {

        InputStream in = null;
        ByteArrayOutputStream buffer = null;

        try {
            String nameTile = getTileFilename(x, y, zoom);
            if(nameTile.equals(""))
                return null;
            in = mAssets.open(nameTile);
            buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[BUFFER_SIZE];

            while ((nRead = in.read(data, 0, BUFFER_SIZE)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();

            return buffer.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) try { in.close(); } catch (Exception ignored) {}
            if (buffer != null) try { buffer.close(); } catch (Exception ignored) {}
        }
    }

    private String getTileFilename(int x, int y, int zoom) {
        if(zoom<16||zoom>19)
            return "";

        //76722 -> 3584
        //38361

        int xtile = (int)Math.floor( (30.723556 + 180) / 360 * (1<<zoom) ) ;
        //46376 -> 2816
        int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(46.482293)) + 1 / Math.cos(Math.toRadians(46.482293))) / Math.PI) / 2 * (1<<zoom) ) ;
        Log.v("Zoom:", String.valueOf(zoom));
        switch(zoom){
            case 19:
                int factorX =  xtile - x + 1;
                int factorY = ytile - y + 1;
                Log.v("Coordinate:", String.valueOf(factorX)+":"+String.valueOf(factorY));
                if(factorX < 0 || factorY < 0 || factorY > 47 || factorX > 59)
                    return "";
                return "map/" + zoom + "/_" + (59-factorX)  + "_" + (47-factorY) + ".png";
            case 18:
                factorX =  xtile - x + 7;
                factorY = ytile - y + 3;
                Log.v("Coordinate:", String.valueOf(factorX)+":"+String.valueOf(factorY));
                if(factorX < 0 || factorY < 0 || factorY > 23 || factorX > 29)
                    return "";
                return "map/" + zoom + "/_" + (29-factorX)  + "_" + (23-factorY) + ".png";
            case 17:
                factorX = xtile - x + 3; // add one to prevent becoming 0-0.png
                factorY = ytile - y + 2;
                Log.v("Coordinate:", String.valueOf(factorX)+":"+String.valueOf(factorY));
                if(factorX < 0 || factorY < 0 || factorY > 12 || factorX > 15)
                    return "";

                return "map/" + zoom +"/" + ((15-factorX)*256) + '-' + ((12-factorY)*256) + ".png";
            case 16:
                factorX = xtile - x + 2; // add one to prevent becoming 0-0.png
                factorY = ytile - y + 1;
                if(factorX < 0 || factorY < 0 || factorY > 12 || factorX > 15)
                    return "";

                return "map/" + zoom +"/" + ((11-factorX)*256) + '-' + ((9-factorY)*256) + ".png";
        }
        return "";
    }
}