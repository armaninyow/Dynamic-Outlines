#version 150

#moj_import <fog.glsl>

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;

out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/core/rendertype_lines.fsh.
// Same fix as our rendertype_outline.fsh override for entities: vanilla multiplies our
// chosen line color (vertexColor) by ColorModulator.rgb, a separate global tint. Dropping
// that RGB multiplication (keeping only its alpha) lets our block outline colors render at
// full, unmodified saturation - matching the same fix already applied to entity outlines.
// Fog distance blending is left untouched.
void main() {
    vec4 color = vec4(vertexColor.rgb, vertexColor.a * ColorModulator.a);
    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}
