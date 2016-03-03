/*
 * Copyright 2000-2016 JetBrains s.r.o.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.buildServer.clouds.azure.arm;

import com.google.gson.annotations.SerializedName;
import jetbrains.buildServer.clouds.base.beans.CloudImagePasswordDetails;
import jetbrains.buildServer.clouds.base.types.CloneBehaviour;
import org.jetbrains.annotations.NotNull;

/**
 * ARM cloud image details.
 */
public class AzureCloudImageDetails implements CloudImagePasswordDetails {

    @SerializedName("groupId")
    private final String myGroupId;
    @SerializedName("storageId")
    private final String myStorageId;
    @SerializedName("imagePath")
    private final String myImagePath;
    @SerializedName("maxInstances")
    private final int myMaxInstances;
    @SerializedName("vmSize")
    private final String myVmSize;
    @SerializedName("vmNamePrefix")
    private final String myVmPrefix;
    @SerializedName("vmUsername")
    private final String myUsername;
    private String myPassword = null;

    public AzureCloudImageDetails(@NotNull final String groupId,
                                  @NotNull final String storageId,
                                  @NotNull final String imagePath,
                                  @NotNull final String vmNamePrefix,
                                  @NotNull final String vmSize,
                                  final int maxInstances,
                                  @NotNull final String username) {
        myGroupId = groupId;
        myStorageId = storageId;
        myImagePath = imagePath;
        myVmPrefix = vmNamePrefix;
        myVmSize = vmSize;
        myMaxInstances = maxInstances;
        myUsername = username;
    }

    public String getGroupId() {
        return myGroupId;
    }

    public String getStorageId() {
        return myStorageId;
    }

    public String getImagePath() {
        return myImagePath;
    }

    public String getVmPrefix() {
        return myVmPrefix;
    }

    public String getVmSize() {
        return myVmSize;
    }

    public int getMaxInstances() {
        return myMaxInstances;
    }

    public String getUsername() {
        return myUsername;
    }

    public String getPassword() {
        return myPassword;
    }

    public void setPassword(final String password) {
        myPassword = password;
    }

    @Override
    public String getSourceName() {
        return String.format("https://%s.blob.core.windows.net/%s", myStorageId, myImagePath);
    }

    public CloneBehaviour getBehaviour() {
        return CloneBehaviour.FRESH_CLONE;
    }
}