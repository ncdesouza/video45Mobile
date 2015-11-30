package com.video45.video.editor.tools;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by nicholas on 28/11/15.
 */
public class Decoder extends MediaCodec.Callback {

    private MediaExtractor extractor;
    private MediaFormat format;
    private MediaCodec codec;
    private SurfaceView surfaceView;

    public Decoder(Uri video, Surface surface) throws IOException, CodecNotFoundException {
        extractor = new MediaExtractor();
        extractor.setDataSource(video.toString());
        format = extractor.getTrackFormat(0);

        MediaCodecList codeclist = new MediaCodecList(MediaCodecList.REGULAR_CODECS);
        String decoderName = codeclist.findDecoderForFormat(format);
        if (decoderName == null) {
            throw new CodecNotFoundException("Could not determine the correct decoder");
        }
        codec = MediaCodec.createByCodecName(decoderName);
        codec.setCallback(this);
        codec.configure(format, surface, null, 0);
        codec.start();
    }

    @Override
    public void onInputBufferAvailable(MediaCodec codec, int index) {
        extractor.advance();
    }

    @Override
    public void onOutputBufferAvailable(MediaCodec codec, int index, MediaCodec.BufferInfo info) {

    }

    @Override
    public void onError(MediaCodec codec, MediaCodec.CodecException e) {

    }

    @Override
    public void onOutputFormatChanged(MediaCodec codec, MediaFormat format) {

    }

    public static class CodecNotFoundException extends Exception {
        public CodecNotFoundException() {
        }

        public CodecNotFoundException(String detailMessage) {
            super(detailMessage);
        }

    }

}
