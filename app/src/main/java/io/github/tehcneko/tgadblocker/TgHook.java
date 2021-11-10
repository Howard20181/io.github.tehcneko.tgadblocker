package io.github.tehcneko.tgadblocker;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TgHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        var classLoader = loadPackageParam.classLoader;
        var messagesController = XposedHelpers.findClassIfExists("org.telegram.messenger.MessagesController", loadPackageParam.classLoader);
        if (messagesController != null) {
            XposedBridge.hookAllMethods(messagesController, "getSponsoredMessages", XC_MethodReplacement.returnConstant(null));
        }
    }
}
