package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanGR extends AppCompatActivity {

    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_gr);

        surfaceView = findViewById(R.id.surfaceView);
        barcodeDetector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector).setRequestedPreviewSize(600,600).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {   // try-catch 문은 Camera 권한획득을 위한 권장사항
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());  // Mobile Vision API 시작
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Log.d("NowStatus", "BarcodeDetector SetProcessor Released");
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                // 바코드가 인식되었을 때 무슨 일을 할까?
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size() != 0) {
                    String barcodeContents = barcodes.valueAt(0).displayValue; // 바코드 인식 결과물
                    Log.d("Detection", barcodeContents);
                }
            }
        });
    }


}