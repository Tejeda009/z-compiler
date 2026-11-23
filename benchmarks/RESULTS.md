# Z benchmark results

Generated on `2026-11-23T14:08:26Z` from commit `0b4e21c` (`Merge pull request #247 from the-big-zed/benchmarks/results`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.209x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.208x |
| Z faster | 12 |
| C faster | 6 |
| Ties | 0 |
| Total C best | 3696.095 ms |
| Total Z best | 3505.655 ms |
| Highest C RSD | 28.84% (string_concat) |
| Highest Z RSD | 37.46% (string_concat) |

Run-to-run variance above 5%: `string_concat`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.000x | 1.000x |
| allocation | 1 | 4.138x | 4.065x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.086x | 1.086x |
| float | 1 | 0.841x | 0.841x |
| integer | 4 | 0.972x | 0.972x |
| memory | 1 | 1.877x | 1.877x |
| pointers | 1 | 1.467x | 1.467x |
| strings | 1 | 2.013x | 2.006x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 164.429 | 164.399 | +0.0% | 164.474 | 164.448 | 0.03% | 0.03% |
| collatz | compute | 264.619 | 264.571 | +0.0% | 264.768 | 264.811 | 0.03% | 0.04% |
| pi | compute | 114.274 | 64.187 | +43.8% | 114.303 | 64.231 | 0.02% | 0.03% |
| primes | compute | 214.440 | 214.611 | -0.1% | 214.622 | 214.644 | 0.03% | 0.01% |
| ackermann | compute | 64.145 | 64.140 | +0.0% | 64.214 | 64.206 | 0.05% | 0.08% |
| mandelbrot | compute | 114.276 | 114.302 | -0.0% | 114.344 | 114.364 | 0.04% | 0.03% |
| hash | compute | 214.427 | 214.483 | -0.0% | 214.628 | 214.607 | 0.05% | 0.04% |
| alias | alias | 64.140 | 64.129 | +0.0% | 64.186 | 64.167 | 0.04% | 0.06% |
| alloc_churn | allocation | 31.753 | 7.673 | +75.8% | 31.911 | 7.850 | 0.36% | 1.17% |
| string_concat | strings | 31.946 | 15.869 | +50.3% | 31.994 | 15.946 | 28.84% | 37.46% |
| fib | calls | 414.827 | 414.850 | -0.0% | 415.164 | 415.057 | 0.05% | 0.04% |
| collatz | integer | 264.580 | 264.555 | +0.0% | 264.615 | 264.587 | 0.03% | 0.04% |
| gcd_sum | integer | 314.651 | 314.631 | +0.0% | 314.670 | 314.680 | 0.01% | 0.01% |
| primes | integer | 214.587 | 214.443 | +0.1% | 214.634 | 214.619 | 0.02% | 0.03% |
| digit_sum | integer | 415.058 | 465.175 | -12.1% | 415.259 | 465.320 | 0.02% | 0.01% |
| monte_carlo | float | 264.684 | 314.862 | -19.0% | 264.782 | 314.952 | 0.02% | 0.02% |
| concat_chain | memory | 214.614 | 114.347 | +46.7% | 214.693 | 114.399 | 0.02% | 0.03% |
| ptr_accum | pointers | 314.645 | 214.425 | +31.9% | 314.692 | 214.548 | 0.04% | 0.04% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 10.
- Pinning: CPU 1.
- Statistics: best-of-N and median wall-clock time, plus relative standard deviation; interleaved execution.
- Correctness: both binaries must exit successfully and print the same result before timing.

## Environment

| Field | Value |
| --- | --- |
| OS | Ubuntu 26.04.1 LTS |
| Kernel | 7.0.0-1020-azure |
| CPU | AMD EPYC 9V74 80-Core Processor |
| Cores | 4 |
| Affinity | [0, 1, 2, 3] |
| CPU governor | unavailable |
| Load average | [0.96, 0.54, 0.23] |
| Clang | Ubuntu clang version 22.1.2 (1ubuntu1) |
| opt | Ubuntu LLVM version 22.1.2 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 10 --warmup 2
```
