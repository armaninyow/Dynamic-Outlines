#version 150

uniform sampler2D InSampler;
uniform float Radius;

in vec2 texCoord;
in vec2 sampleStep;

out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/post/entity_outline_box_blur.fsh.
// Previously this averaged samples in a box blur (with premultiplied-alpha accumulation
// to avoid a dark fringe - see prior history). That approach has a structural weakness:
// when two separate silhouette edges pass close together in screen space (e.g. a narrow
// gap in the model), a pixel between them only has SOME covered neighbors, so averaging
// dilutes it - producing a visible brightness "dip"/seam between two otherwise-full-
// brightness sections of the same outline.
//
// This version is a max-filter dilation instead of an averaging blur: for each pixel, it
// looks within [-Radius, Radius] along this pass's direction and keeps whichever sample
// has the HIGHEST coverage (alpha), using that sample's color. Two nearby edges then
// reinforce each other (both contribute full coverage) instead of diluting each other -
// structurally eliminating the seam, not just tuning around it. Applied twice (horizontal
// then vertical, per entity_outline.json) this becomes a full 2D dilation.
//
// NOTE: as of 1.21.2 the sampler uniform is named InSampler (was DiffuseSampler).
void main() {
        vec4 best = vec4(0.0);

        for (float a = -Radius; a <= Radius; a += 1.0) {
                vec4 s = texture(InSampler, texCoord + sampleStep * a);
                if (s.a > best.a) {
                        best = s;
                }
        }

        fragColor = best;
}
