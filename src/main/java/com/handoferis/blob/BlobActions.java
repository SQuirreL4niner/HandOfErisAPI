package com.handoferis.blob;

import com.microsoft.azure.storage.AccessCondition;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.BlobRequestOptions;

import java.io.InputStream;

public interface BlobActions {

    void uploadFromByteArray(final byte[] buffer, final int offset, final int length);

    void uploadFromByteArray(final byte[] buffer, final int offset, final int length, final AccessCondition accessCondition,
                             BlobRequestOptions options, OperationContext opContext);

    void uploadFromFile(final String path);

    void uploadFromFile(final String path, final AccessCondition accessCondition, BlobRequestOptions blobReqeustOptions,
                        OperationContext opContext);

    void upload(InputStream sourceStream, long length, final AccessCondition accessCondition, BlobRequestOptions blobRequestOptions,
                OperationContext opContext);

}
