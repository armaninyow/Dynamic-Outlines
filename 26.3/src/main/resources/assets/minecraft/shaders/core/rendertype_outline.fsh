#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:dynamictransforms.glsl>

uniform sampler2D Sampler0;

layout(location = 0) in vec4 vertexColor;
layout(location = 1) in vec2 texCoord0;

layout(location = 0) out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/core/rendertype_outline.fsh.
// Vanilla multiplies vertexColor (our chosen outline color) by ColorModulator.rgb, which can
// dim/tint it before the blur pass. This drops the RGB multiplication and keeps
// ColorModulator's alpha, so the outline color renders at full, unmodified strength.
void main() {
    vec4 color = texture(Sampler0, texCoord0);
    if (color.a == 0.0) {
        discard;
    }
    fragColor = vec4(vertexColor.rgb, vertexColor.a * ColorModulator.a);
}