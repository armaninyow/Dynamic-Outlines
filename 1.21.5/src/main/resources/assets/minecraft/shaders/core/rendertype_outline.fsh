#version 150

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/core/rendertype_outline.fsh.
// Vanilla multiplies vertexColor (our chosen outline color, forced via
// OutlineBufferSource#setColor) by ColorModulator.rgb - a separate global tint applied
// during rendering (lighting/fog/graphics-mode contexts). If that modulator isn't pure
// white at render time, our chosen color gets dimmed/tinted before the blur pass even
// runs, which is why it didn't look like the same pure color as block outlines. This
// version keeps ColorModulator's alpha (still respects fade/transparency contexts) but
// drops its RGB multiplication, so our outline color renders at full, unmodified strength.
void main() {
	vec4 color = texture(Sampler0, texCoord0);
	if (color.a == 0.0) {
		discard;
	}
	fragColor = vec4(vertexColor.rgb, vertexColor.a * ColorModulator.a);
}
