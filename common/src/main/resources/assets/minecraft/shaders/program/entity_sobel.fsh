#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

// Overrides vanilla's assets/minecraft/shaders/program/entity_sobel.fsh.
// Vanilla divides the summed RGB of the 5 sampled texels by a FIXED constant of 5
// (the "* 0.2") regardless of how many of those texels actually had any coverage
// (alpha > 0). Every pixel that survives into the visible ring is, by definition, a
// partial-coverage edge pixel (fully-interior pixels get total=0 alpha and get discarded
// entirely) - so real coverage at these pixels is almost always well under 5, yet vanilla
// divides by 5 anyway, permanently diluting/desaturating the color at exactly the pixels
// that make up the visible outline. This divides by the ACTUAL coverage sum instead,
// recovering the true, undiluted color. The alpha channel (edge-detection magnitude,
// "total") is left exactly as vanilla computes it - only the RGB normalization is fixed.
void main(){
    vec4 center = texture(DiffuseSampler, texCoord);
    vec4 left = texture(DiffuseSampler, texCoord - vec2(oneTexel.x, 0.0));
    vec4 right = texture(DiffuseSampler, texCoord + vec2(oneTexel.x, 0.0));
    vec4 up = texture(DiffuseSampler, texCoord - vec2(0.0, oneTexel.y));
    vec4 down = texture(DiffuseSampler, texCoord + vec2(0.0, oneTexel.y));
    float leftDiff  = abs(center.a - left.a);
    float rightDiff = abs(center.a - right.a);
    float upDiff    = abs(center.a - up.a);
    float downDiff  = abs(center.a - down.a);
    float total = clamp(leftDiff + rightDiff + upDiff + downDiff, 0.0, 1.0);

    float coverage = center.a + left.a + right.a + up.a + down.a;
    vec3 outColor = (center.rgb * center.a + left.rgb * left.a + right.rgb * right.a + up.rgb * up.a + down.rgb * down.a) / max(coverage, 0.0001);

    fragColor = vec4(outColor, total);
}
