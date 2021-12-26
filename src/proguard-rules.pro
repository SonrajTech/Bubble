# Add any ProGuard configurations specific to this
# extension here.

-keep public class in.sonraj.touchbubble.TouchBubble {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'in/sonraj/touchbubble/repack'
-flattenpackagehierarchy
-dontpreverify
