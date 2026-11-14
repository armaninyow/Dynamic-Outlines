#version 330
#extension GL_ARB_separate_shader_objects : require

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BlurConfig {
    vec2 BlurDir;
    float Radius;
};

uniform sampler2D InSampler;

layout(location = 0) in vec2 texCoord;

layout(location = 0) out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/post/entity_outline_box_blur.fsh.
// Max-filter dilation instead of an averaging blur: within [-Radius, Radius] along this
// pass's direction, keep the sample with the highest alpha. Nearby edges reinforce instead
// of diluting each other, which removes brightness seams. Applied horizontally then
// vertically (see entity_outline.json) it becomes a full 2D dilation.
// Radius is driven by PostChainUniformMixin (strokeWidth * 0.25).
void main() {
    vec2 oneTexel = 1.0 / InSize;
    vec2 sampleStep = oneTexel * BlurDir;

    vec4 best = vec4(0.0);
    for (float a = -Radius; a <= Radius; a += 1.0) {
        vec4 s = texture(InSampler, texCoord + sampleStep * a);
        if (s.a > best.a) {
            best = s;
        }
    }

    fragColor = best;
}