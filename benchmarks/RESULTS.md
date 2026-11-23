# Z benchmark results

Generated on `2026-11-23T14:23:41Z` from commit `0b4e21c` (`Merge pull request #247 from the-big-zed/benchmarks/results`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.204x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.203x |
| Z faster | 14 |
| C faster | 4 |
| Ties | 0 |
| Total C best | 4375.104 ms |
| Total Z best | 4060.174 ms |
| Highest C RSD | 0.19% (string_concat) |
| Highest Z RSD | 29.02% (string_concat) |

Run-to-run variance above 5%: `string_concat`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.001x | 1.000x |
| allocation | 1 | 2.029x | 2.025x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.056x | 1.056x |
| float | 1 | 1.000x | 1.000x |
| integer | 4 | 1.000x | 1.000x |
| memory | 1 | 2.317x | 2.316x |
| pointers | 1 | 1.000x | 1.000x |
| strings | 1 | 4.069x | 4.037x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 314.253 | 214.276 | +31.8% | 314.541 | 214.365 | 0.04% | 0.04% |
| collatz | compute | 264.239 | 264.304 | -0.0% | 264.454 | 264.442 | 0.04% | 0.03% |
| pi | compute | 113.931 | 63.903 | +43.9% | 113.992 | 63.968 | 0.06% | 0.16% |
| primes | compute | 264.224 | 264.212 | +0.0% | 264.499 | 264.442 | 0.13% | 0.06% |
| ackermann | compute | 63.954 | 113.975 | -78.2% | 64.073 | 114.140 | 0.15% | 0.11% |
| mandelbrot | compute | 114.011 | 113.928 | +0.1% | 114.095 | 114.072 | 0.06% | 0.07% |
| hash | compute | 214.153 | 214.128 | +0.0% | 214.222 | 214.185 | 0.04% | 0.03% |
| alias | alias | 114.071 | 113.998 | +0.1% | 114.172 | 114.159 | 0.05% | 0.07% |
| alloc_churn | allocation | 31.785 | 15.666 | +50.7% | 31.804 | 15.703 | 0.05% | 0.24% |
| string_concat | strings | 63.840 | 15.688 | +75.4% | 63.994 | 15.850 | 0.19% | 29.02% |
| fib | calls | 514.924 | 514.917 | +0.0% | 515.039 | 515.050 | 0.02% | 0.01% |
| collatz | integer | 264.336 | 264.378 | -0.0% | 264.452 | 264.489 | 0.02% | 0.02% |
| gcd_sum | integer | 364.631 | 364.449 | +0.1% | 364.704 | 364.709 | 0.01% | 0.03% |
| primes | integer | 264.374 | 264.432 | -0.0% | 264.500 | 264.509 | 0.03% | 0.02% |
| digit_sum | integer | 464.884 | 464.778 | +0.0% | 464.925 | 464.944 | 0.01% | 0.02% |
| monte_carlo | float | 314.556 | 314.515 | +0.0% | 314.627 | 314.633 | 0.02% | 0.02% |
| concat_chain | memory | 264.300 | 114.059 | +56.8% | 264.481 | 114.199 | 0.04% | 0.06% |
| ptr_accum | pointers | 364.637 | 364.566 | +0.0% | 364.719 | 364.742 | 0.02% | 0.03% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 10.
- Pinning: CPU 3.
- Statistics: best-of-N and median wall-clock time, plus relative standard deviation; interleaved execution.
- Correctness: both binaries must exit successfully and print the same result before timing.

## Environment

| Field | Value |
| --- | --- |
| OS | Ubuntu 26.04.1 LTS |
| Kernel | 7.0.0-1020-azure |
| CPU | AMD EPYC 7763 64-Core Processor |
| Cores | 4 |
| Affinity | [0, 1, 2, 3] |
| CPU governor | unavailable |
| Load average | [1.29, 0.84, 0.36] |
| Clang | Ubuntu clang version 22.1.2 (1ubuntu1) |
| opt | Ubuntu LLVM version 22.1.2 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 10 --warmup 2
```
