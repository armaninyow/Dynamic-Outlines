#version 150

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:dynamictransforms.glsl>

in float sphericalVertexDistance;
in float cylindricalVertexDistance;
in vec4 vertexColor;

out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/core/rendertype_lines.fsh.
// Same fix as our rendertype_outline.fsh override for entities: vanilla multiplies our
// chosen line color (vertexColor) by ColorModulator.rgb, a separate global tint. Dropping
// that RGB multiplication (keeping only its alpha) lets our block outline colors render at
// full, unmodified saturation - matching the same fix already applied to entity outlines.
// Fog distance blending is left untouched.
//
// NOTE: as of 1.21.6, fog now uses two distance measures (spherical + cylindrical) and four
// boundary uniforms (FogEnvironmentalStart/End, FogRenderDistanceStart/End) via apply_fog(),
// replacing the old single-distance linear_fog(). ColorModulator now comes from the
// DynamicTransforms uniform interface block (imported via #moj_import) instead of being
// declared as a standalone uniform - declaring it manually here conflicts with that block.
void main() {
    vec4 color = vec4(vertexColor.rgb, vertexColor.a * ColorModulator.a);
    fragColor = apply_fog(color, sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart, FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd, FogColor);
}
